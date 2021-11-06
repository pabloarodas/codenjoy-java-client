call 0-settings.bat

echo off
call lib :color generating elements for all clients...
echo on

set BASE=%ROOT%\..
rem call 'set GAMES=all' to select all games
set GAMES=a2048,battlecity,clifford,mollymage,sample,sokoban,spacerace,startandjump,sudoku,tetris
set CLIENTS=cpp,go,js,php,python

call %MVNW% clean compile exec:java -Dfile.encoding=UTF-8 -D"exec.mainClass"="com.codenjoy.dojo.client.generator.Runner" -D"exec.args"="%BASE% %GAMES% %CLIENTS%"

call lib :ask
