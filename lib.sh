#!/usr/bin/env bash

# red 91
# green 92
# yellow 93
# blue 94
# pink 95
# light blue 96
# purple 97
COLOR1=92
COLOR2=94
COLOR3=96
COLOR4=93

eval_echo_color_output() {
	  command=$1
	  output=$($command)
	  color $COLOR2 "$command"
    color $COLOR4 "$output"
}

color() {
    color=$1
    message=$2
    echo "[${color}m$message[0m"
}

eval_echo() {
    command=$1
    eval_echo_color $COLOR2 "$command"
}

eval_echo_color() {
    color=$1
    command=$2
    color $color "$command"
    echo

    eval $command
}

ask() {
    color 94 "Press any key to continue"
    read -p ""
}

sep() {
    color 94 "---------------------------------------------------------------------------------------"
}

install() {
    cd $ROOT

    eval_echo_color $COLOR3 "DEST_ZIP=$1.zip"
    eval_echo_color $COLOR3 "DEST=.$1"
    eval_echo_color $COLOR3 "URL=$2"
    eval_echo_color $COLOR3 "FOLDER=$3"

    if test -f "$TOOLS/$DEST_ZIP"; then
        eval_echo_color $COLOR3 "rm $TOOLS/$DEST_ZIP"
    fi

    eval_echo_color $COLOR3 "curl -o $TOOLS/$DEST_ZIP $URL"

    eval_echo_color $COLOR3 "rm -rf $TOOLS/../$DEST"

    if [[ $FOLDER == "" ]]; then
        eval_echo_color $COLOR3 "$ARCH -xf $TOOLS/$DEST_ZIP --directory $TOOLS/../$DEST"
    else
        eval_echo_color $COLOR3 "$ARCH -xf $TOOLS/$DEST_ZIP --directory $TOOLS/.."
        sleep 1
        eval_echo_color $COLOR3 "mv $TOOLS/../$FOLDER $DEST"
    fi

    cd $ROOT
}
