@echo off

:: Install JDK if missing - TODO
:: create missing bin folder
mkdir %CD%\bin 1>nul 2>nul
:: compile
javac ^
  -encoding "utf-8" ^
  -deprecation ^
  -Xlint:all ^
  -d %CD%\bin\ ^
  %CD%\src\Main.java

if %errorlevel% neq 0 exit /b %errorlevel%
:: package
cd bin
echo Main-Class: src.Main > manifest
jar -cfm nono.jar manifest ^
  src\Main.class ^
  src\game\Cell.class ^
  src\game\Cursor.class ^
  src\game\Game.class ^
  src\game\GameCommands.class ^
  src\game\GameServer$1.class ^
  src\game\GameServer.class ^
  src\html\Body.class ^
  src\html\Div.class ^
  src\html\HTML.class ^
  src\html\HTMX.class ^
  src\html\HTMXTrigger.class ^
  src\html\Paragraph.class ^
  src\http\ContextType.class ^
  src\http\Protocol.class ^
  src\http\Request.class ^
  src\http\RequestMethod.class ^
  src\http\Response.class ^
  src\http\Server.class ^
  src\http\StatusCode.class

if %errorlevel% neq 0 exit /b %errorlevel%
cd ..
copy %cd%\bin\nono.jar %cd%\nono.jar 1>nul
