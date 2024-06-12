@echo off

:: Install JDK if missing
WHERE javac 1>nul
IF %ERRORLEVEL% NEQ 0 winget install Oracle.JDK.21
:: create missing bin folder
mkdir %CD%\bin 1>nul 2>nul
:: compile
javac ^
  -deprecation ^
  -d %CD%\bin\ ^
  %CD%\src\Main.java ^
  %CD%\src\Frame.java 

if %errorlevel% neq 0 exit /b %errorlevel%
:: package
cd bin
jar -cfm nono.jar manifest ^
  src\Main.class ^
  src\Frame.class
cd ..
copy %cd%\bin\nono.jar %cd%\nono.jar 1>nul
:: run
java -ea -jar nono.jar
