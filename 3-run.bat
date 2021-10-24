call 0-settings.bat

echo off
call lib :color Starting java client...
echo on

call %JAVA% -Dfile.encoding=UTF-8 -jar %ROOT%/app.jar "%GAME_TO_RUN%" "%BOARD_URL%"

rem another way to run
rem call %MVNW% exec:java -Dfile.encoding=UTF-8 -D"exec.mainClass"="com.codenjoy.dojo.Runner" -D"exec.args"="%GAME_TO_RUN% %BOARD_URL%"

call lib :ask
