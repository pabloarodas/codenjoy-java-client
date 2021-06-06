if "%JAVA_CLIENT_HOME%"=="" (
    call 0-settings.bat
)

echo off
echo [44;93m
echo        +-------------------------------------------------------------------------+        
echo        !                 Now we are building java client...                      !
echo        +-------------------------------------------------------------------------+        
echo [0m
echo on
IF "%DEBUG%"=="true" ( 
    pause >nul
)

call mvnw clean install -DskipTests=%SKIP_TESTS%

echo Press any key to exit
pause >nul