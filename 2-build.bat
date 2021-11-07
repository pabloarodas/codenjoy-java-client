call 0-settings.bat

echo off
call lib :color Building java client...
echo on

call lib :print_color %MVNW% -v

rem to create executable jar please uncomment lines
rem call %MVNW% clean compile assembly:single -Pjar-with-dependencies -DskipTests=%SKIP_TESTS%
rem echo F|xcopy /y %ROOT%\target\client-java-exec.jar %ROOT%\app.jar

call lib :ask
