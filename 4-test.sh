#!/usr/bin/env bash

. 0-settings.sh

color $COLOR1 "Starting java tests..."
echo

eval_echo "$MVNW clean test"

ask