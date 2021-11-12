call 0-settings.bat

echo off
call lib :color Starting java client...
echo on

rem another way to run after 2-build.bat
rem call %JAVA% -Dfile.encoding=UTF-8 -jar %ROOT%/app.jar "%GAME_TO_RUN%" "%BOARD_URL%"

rem another way to run without after 2-build.bat
call %MVNW% clean compile exec:java -Dfile.encoding=UTF-8 -D"exec.mainClass"="com.codenjoy.dojo.JavaRunner" -D"exec.args"="%GAME_TO_RUN% %BOARD_URL%"

call lib :ask
