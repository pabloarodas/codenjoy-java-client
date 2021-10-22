call 0-settings.bat

echo off
echo        [44;93m+--------------------------------------------+[0m
echo        [44;93m!     Now we are starting java client...     ![0m
echo        [44;93m+--------------------------------------------+[0m
echo on

call %MVNW% exec:java -Dfile.encoding=UTF-8 -D"exec.mainClass"="com.codenjoy.dojo.Runner" -D"exec.args"="%GAME_TO_RUN% %BOARD_URL%"

call lib.bat :ask
