@echo off

:: create missing bin folder
mkdir %CD%\bin 1>nul 2>nul
:: compile
javac ^
  -encoding "utf-8" ^
  -deprecation ^
  -Xlint:all ^
  -d %CD%\bin\ ^
  %CD%\tests\Main.java
if %errorlevel% neq 0 exit /b %errorlevel%
:: run
cd bin
java -ea tests.Main
cd ..