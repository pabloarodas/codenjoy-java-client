#!/usr/bin/env bash

eval_echo() {
    command=$1
    color=94 # blue
    echo "[${color}m$command[0m"
    echo
    eval $command
}

eval_echo "echo Generating elements for all clients..."

eval_echo "cd .."
eval_echo "BASE=$(pwd)/.."
eval_echo "MVNW=./mvnw"

# to select all games run
# eval_echo "GAMES=all'
eval_echo "GAMES=a2048,battlecity,chess,clifford,collapse,expansion,fifteen,football,hex,icancode,japanese,kata,lemonade,lunolet,minesweeper,moebius,mollymage,pong,puzzlebox,quadro,quake2d,reversi,rubicscube,sample,sample-text,selfdefense,snake,snakebattle,sokoban,spacerace,startandjump,sudoku,tetris,vacuum,verland,xonix"
eval_echo "CLIENTS=md,md_header,md_footer,cpp,go,js,php,python,csharp"

# TODO excitebike(rename elements,add info)
# TODO icancode(js)(add info)
# TODO expansion(add info)

eval_echo "$MVNW clean compile exec:java -Dfile.encoding=UTF-8 -Dexec.mainClass=com.codenjoy.dojo.client.generator.ElementGeneratorRunner -Dexec.args='$BASE $GAMES $CLIENTS'"

echo Press Enter to continue
read