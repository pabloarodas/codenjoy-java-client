call 0-settings.bat

echo off
call lib :color Starting java tests...
echo on

call %MVNW% clean test

call lib :ask