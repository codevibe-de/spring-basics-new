@echo off
REM Starts a standalone H2 TCP server using the JVM and its own runtime classpath.
REM Data is stored in .\h2-data\ (persists between restarts).

setlocal
cd /d "%~dp0"

echo Building project classpath...
set "CP_FILE=%TEMP%\h2-classpath-%RANDOM%.txt"
call mvnw.cmd -q dependency:build-classpath -Dmdep.outputFile="%CP_FILE%" >nul 2>&1

if not exist "%CP_FILE%" goto :no_classpath
set /p CLASSPATH=<"%CP_FILE%"
del "%CP_FILE%" >nul 2>&1
if not defined CLASSPATH goto :no_classpath

set "DATA_DIR=%CD%\h2-data"
if not exist "%DATA_DIR%" mkdir "%DATA_DIR%"

echo Starting H2 TCP server (from app classpath)...
echo   TCP:  jdbc:h2:tcp://localhost:9092/./pizzadb
echo   Web console: http://localhost:8082
echo   Data directory: %DATA_DIR%
echo.
echo Press Ctrl+C to stop.
echo.

java -cp "%CLASSPATH%" org.h2.tools.Server ^
  -tcp -tcpPort 9092 -tcpAllowOthers ^
  -web -webPort 8082 ^
  -baseDir "%DATA_DIR%" ^
  -ifNotExists

endlocal
exit /b 0

:no_classpath
if exist "%CP_FILE%" del "%CP_FILE%" >nul 2>&1
echo Could not build the project classpath.
echo Run 'mvnw.cmd dependency:resolve' first to populate the local Maven cache.
endlocal
exit /b 1
