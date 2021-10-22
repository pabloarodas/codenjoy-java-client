call 0-settings.bat

echo off
call lib :color Building java client...
echo on

call lib :print_color %MVNW% -v
call %MVNW% clean install -DskipTests=%SKIP_TESTS%

call lib :ask
