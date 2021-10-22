call 0-settings.bat

echo off
echo        [44;93m+----------------------------------------------+[0m
echo        [44;93m!      Now we are building java client...      ![0m
echo        [44;93m+----------------------------------------------+[0m
echo on

call lib.bat :print_color %MVNW% -v

call %MVNW% clean install -DskipTests=%SKIP_TESTS%

call lib.bat :ask
