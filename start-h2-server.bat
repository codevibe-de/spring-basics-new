@echo off
:: Starts a standalone H2 TCP server for custom datasource exercises.
:: Data is stored in .\h2-data\ (persists between restarts).
::
:: The H2 jar is resolved from the version declared in pom.xml (single source
:: of truth) via Maven's dependency:build-classpath -- no hardcoded version here.
::
:: Connect from your app with:
::   spring.datasource.url=jdbc:h2:tcp://localhost:9092/./pizzadb
::   spring.datasource.driver-class-name=org.h2.Driver
::   spring.datasource.username=sa
::   spring.datasource.password=
::
:: H2 web console: http://localhost:8082
::   JDBC URL to paste there: jdbc:h2:tcp://localhost:9092/./pizzadb

setlocal
cd /d "%~dp0"

echo Resolving H2 jar from pom.xml...
set "CP_FILE=%TEMP%\h2-jar-%RANDOM%.txt"
call mvnw.cmd -q dependency:build-classpath -Dmdep.includeArtifactIds=h2 -Dmdep.outputFile="%CP_FILE%" >nul 2>&1

if not exist "%CP_FILE%" goto :no_jar
set /p H2_JAR=<"%CP_FILE%"
del "%CP_FILE%" >nul 2>&1
if not defined H2_JAR goto :no_jar
if not exist "%H2_JAR%" goto :no_jar
echo   Using: %H2_JAR%

set "DATA_DIR=%CD%\h2-data"
if not exist "%DATA_DIR%" mkdir "%DATA_DIR%"

echo Starting H2 TCP server...
echo   TCP:  jdbc:h2:tcp://localhost:9092/./pizzadb
echo   Web console: http://localhost:8082
echo   Data directory: %DATA_DIR%
echo.
echo Press Ctrl+C to stop.
echo.

java -cp "%H2_JAR%" org.h2.tools.Server -tcp -tcpPort 9092 -tcpAllowOthers -web -webPort 8082 -baseDir "%DATA_DIR%" -ifNotExists

endlocal
exit /b 0

:no_jar
if exist "%CP_FILE%" del "%CP_FILE%" >nul 2>&1
echo Could not resolve the H2 jar.
echo Run 'mvnw.cmd dependency:resolve' first to populate the local Maven cache.
endlocal
exit /b 1
