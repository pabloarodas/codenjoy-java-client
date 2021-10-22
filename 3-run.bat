call 0-settings.bat

echo off
call lib :color Starting java client...
echo on

call %MVNW% exec:java -Dfile.encoding=UTF-8 -D"exec.mainClass"="com.codenjoy.dojo.Runner" -D"exec.args"="%GAME_TO_RUN% %BOARD_URL%"

call lib :ask
