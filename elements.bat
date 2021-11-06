call 0-settings.bat

echo off
call lib :color generating elements for all clients...
echo on

set BASE=%ROOT%\..
set CLIENTS=cpp,go,java-script,php,python

call %MVNW% exec:java -Dfile.encoding=UTF-8 -D"exec.mainClass"="com.codenjoy.dojo.client.generator.Runner" -D"exec.args"="%BASE% %CLIENTS%"

call lib :ask
