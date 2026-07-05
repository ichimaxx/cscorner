## Classpath
Zamień `<PROJECT_DIR>` na ścieżkę do folderu `cscorner`.

PowerShell, tylko aktualna sesja:
$env:CLASSPATH=".;<PROJECT_DIR>;<PROJECT_DIR>\XOM\xom-1.4.6.jar;<PROJECT_DIR>\XOM\lib\*"

PowerShell, zapis na stałe:
setx CLASSPATH ".;<PROJECT_DIR>;<PROJECT_DIR>\XOM\xom-1.4.6.jar;<PROJECT_DIR>\XOM\lib\*"
