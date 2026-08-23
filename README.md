## Classpath

Replace `<PROJECT_DIR>` with the full path to the `cscorner` folder.

Example:

```text
C:\Users\ichim\Desktop\cscorner
```

### PowerShell — current session only

```powershell
$env:CLASSPATH=".;<PROJECT_DIR>;<PROJECT_DIR>\XOM\xom-1.4.6.jar;<PROJECT_DIR>\XOM\lib\*;<PROJECT_DIR>\Eclipse_SWT\swt.jar"
```

This command sets `CLASSPATH` only for the currently open PowerShell session.

After closing the terminal, the setting is lost and must be set again the next time PowerShell is opened.

The classpath includes:

* the main `cscorner` project directory
* the XOM library
* XOM dependency libraries
* the SWT library

This allows Java files to be compiled directly from individual chapter directories, for example:

```powershell
cd <PROJECT_DIR>\Rozdzial_22
javac Zad22_40.java
```

### PowerShell — permanent setting

```powershell
setx CLASSPATH ".;<PROJECT_DIR>;<PROJECT_DIR>\XOM\xom-1.4.6.jar;<PROJECT_DIR>\XOM\lib\*;<PROJECT_DIR>\Eclipse_SWT\swt.jar"
```

`setx` saves the `CLASSPATH` value permanently for the current Windows user account.

The change does **not** affect PowerShell windows or other programs that are already running.

If you use the integrated terminal in **IntelliJ IDEA**, close IntelliJ completely and start it again after running `setx`. New terminal sessions opened after the restart will inherit the updated `CLASSPATH`.

You can verify the classpath in a new PowerShell session with:

```powershell
$env:CLASSPATH
```

It should include both XOM and SWT.

> **Important:** `setx CLASSPATH "..."` replaces the previously saved `CLASSPATH` value. When adding another library, include all existing classpath entries as well.
