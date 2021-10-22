call 0-settings.bat

echo off
call lib.bat :color Starting java tests...
echo on

call %MVNW% clean test

call lib.bat :ask