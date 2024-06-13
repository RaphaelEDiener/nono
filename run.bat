@echo off

:: Install JDK if missing
WHERE javac 1>nul
IF %ERRORLEVEL% NEQ 0 winget install EclipseAdoptium.Temurin.21.JDK
WHERE jar 1>nul
IF %ERRORLEVEL% NEQ 0 winget install EclipseAdoptium.Temurin.21.JDK
:: create missing bin folder
mkdir %CD%\bin 1>nul 2>nul
:: compile
javac ^
  -deprecation ^
  -Xlint:unchecked ^
  -d %CD%\bin\ ^
  %CD%\src\Main.java ^
  %CD%\src\Pair.java ^
  %CD%\src\Window.java ^
  %CD%\src\Keys.java ^
  %CD%\src\Cursor.java ^
  %CD%\src\Frame.java 

if %errorlevel% neq 0 exit /b %errorlevel%
:: package
cd bin
jar -cfm nono.jar manifest ^
  src\Main.class ^
  src\Pair.class ^
  src\Window.class ^
  src\Keys.class ^
  src\Cursor.class ^
  src\Frame.class
if %errorlevel% neq 0 exit /b %errorlevel%
cd ..
copy %cd%\bin\nono.jar %cd%\nono.jar 1>nul
:: run
java -ea -jar nono.jar
