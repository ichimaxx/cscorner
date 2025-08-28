# walmart_price_checker.py
# ------------------------------------------------------------
# • otwiera Chrome NA TWOIM PROFILU  “Default”
# • dodaje anti-detection flagę  --disable-blink-features=AutomationControlled
# • NIE podaje ręcznie chromedriver.exe – webdriver-manager pobiera
#   driver w wersji pasującej do zainstalowanego Chrome (Stable 137.x)
# ------------------------------------------------------------
import json, time
from selenium import webdriver, common
from selenium.webdriver.common.by   import By
from selenium.webdriver.support.ui  import WebDriverWait
from selenium.webdriver.support     import expected_conditions as EC
from selenium.webdriver.chrome.options  import Options
from selenium.webdriver.chrome.service  import Service
from webdriver_manager.chrome            import ChromeDriverManager

# ---------- KONFIGURACJA ------------------------------------
PRODUCT_ID  = "3567476245"   # Mortal Kombat 1 – Nintendo Switch
USER_DATA   = r"C:\Users\ichim\AppData\Local\Google\Chrome\User Data"
PROFILE_DIR = "Default"      # ostatni segment z chrome://version

STORES = {
    "1715": ("Villa Park",    "Villa Park Supercenter"),
    "1539": ("Schaumburg",    "Schaumburg Supercenter"),
    "1740": ("Cicero",        "Cicero Supercenter"),
    "2142": ("Forest Park",   "Forest Park Supercenter"),
    "3791": ("Hodgkins",      "Hodgkins Supercenter"),
    "2811": ("Melrose Park",  "Melrose Park Supercenter"),
    "1682": ("Darien",        "Darien Supercenter"),
    "1646": ("Glen Ellyn",    "Glen Ellyn Supercenter"),
}

opts = Options()
opts.add_argument("--start-maximized")
opts.add_argument("--no-sandbox")
opts.add_argument("--disable-gpu")
opts.add_argument("--disable-blink-features=AutomationControlled")
opts.add_argument(f"--user-data-dir={USER_DATA}")
opts.add_argument(f"--profile-directory={PROFILE_DIR}")

driver = webdriver.Chrome(
    service=Service(ChromeDriverManager().install()),   # ← automatyczny driver
    options=opts
)
wait = WebDriverWait(driver, 40)

# ---------- FUNKCJA POBIERAJĄCA CENĘ -------------------------
def price_for(store_id: str, ui_text: str) -> str:
    driver.get(f"https://www.walmart.com/ip/{PRODUCT_ID}")
    time.sleep(4)  # DOM-start

    # klik „Change”
    try:
        wait.until(EC.element_to_be_clickable((By.LINK_TEXT, "Change"))).click()
    except common.exceptions.TimeoutException:
        return "Change – brak"

    # radio-button sklepu + Save
    try:
        radio = wait.until(EC.element_to_be_clickable(
            (By.XPATH, f"//span[contains(.,'{ui_text}')]/preceding::input[@type='radio'][1]")))
        driver.execute_script("arguments[0].click();", radio)
        wait.until(EC.element_to_be_clickable(
            (By.CSS_SELECTOR, "button[data-tl-id='store-list-dialog-save-button']"))
        ).click()
    except common.exceptions.TimeoutException:
        return "sklep – brak"

    # JSON ld+json  → cena
    try:
        js = wait.until(EC.presence_of_element_located(
            (By.CSS_SELECTOR, "script[type='application/ld+json']")))
        data = json.loads(js.get_attribute("innerHTML"))
        if data.get("@type") == "Product":
            return f"${data['offers']['price']}"
    except Exception:
        pass

    # fallback  meta[itemprop=price]
    try:
        meta = driver.find_element(By.CSS_SELECTOR, 'meta[itemprop="price"]')
        return f"${meta.get_attribute('content')}"
    except Exception:
        return "brak ceny"

# ---------- GŁÓWNA PĘTLA --------------------------------------
print("\nMortal Kombat 1 (Switch) – ceny pickup ZIP 60101\n")
print(f"{'Sklep':<15} | Cena")
print("-"*32)

for sid, (label, ui_txt) in STORES.items():
    print(f"{label:<15} | {price_for(sid, ui_txt)}")

driver.quit()
