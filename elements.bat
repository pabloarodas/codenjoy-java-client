call 0-settings.bat

echo off
call lib :color generating elements for all clients...
echo on

set BASE=%ROOT%\..
rem call 'set GAMES=all' to select all games
set GAMES=a2048,battlecity,chess,clifford,collapse,expansion,fifteen,football,hex,icancode,japanese,lemonade,lunolet,minesweeper,moebius,mollymage,pong,puzzlebox,quadro,quake2d,reversi,rubicscube,sample,selfdefense,snake,snakebattle,sokoban,spacerace,startandjump,sudoku,tetris,vacuum,xonix
set CLIENTS=md,md_header,md_footer,cpp,go,js,php,python
rem TODO set excitebike(rename elements,add info),kata(text),sampletext(text),icancode(js)(add info),expansion(add info)

call %MVNW% clean compile exec:java -Dfile.encoding=UTF-8 -D"exec.mainClass"="com.codenjoy.dojo.client.generator.Runner" -D"exec.args"="%BASE% %GAMES% %CLIENTS%"

call lib :ask
