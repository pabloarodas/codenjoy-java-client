call 0-settings.bat

echo off
echo [44;93m
echo        +-------------------------------------------------------------------------+        
echo        !                 Now we are starting java client...                      !
echo        +-------------------------------------------------------------------------+        
echo [0m
echo on
IF "%DEBUG%"=="true" ( 
    pause >nul
)

chcp %CODE_PAGE%
cls

call mvnw exec:java -Dfile.encoding=UTF-8 -D"exec.mainClass"="com.codenjoy.dojo.Runner" -D"exec.args"="%BOARD_URL%"

echo Press any key to exit
pause >nul