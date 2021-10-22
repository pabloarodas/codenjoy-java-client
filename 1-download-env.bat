call 0-settings.bat

echo off
call lib.bat :color Installing JDK...
echo on

if "%SKIP_JDK_INSTALL%"=="true" ( goto :skip )
if "%INSTALL_LOCALLY%"=="false" ( goto :skip )
if "%INSTALL_LOCALLY%"=="" ( goto :skip )

cd %ROOT%
IF EXIST %TOOLS%\jdk.zip (
    del /Q %TOOLS%\jdk.zip
)
powershell -command "& { set-executionpolicy remotesigned -s currentuser; [System.Net.ServicePointManager]::SecurityProtocol = 3072 -bor 768 -bor 192 -bor 48; $client=New-Object System.Net.WebClient; $client.Headers.Add([System.Net.HttpRequestHeader]::Cookie, 'oraclelicense=accept-securebackup-cookie'); $client.DownloadFile('%ARCH_JDK%','%TOOLS%\jdk.zip') }"
rd /S /Q %TOOLS%\..\.jdk
%ARCH% x -y -o%TOOLS%\.. %TOOLS%\jdk.zip
rename %TOOLS%\..\%ARCH_JDK_FOLDER% .jdk
cd %ROOT%

call lib.bat :ask

goto :eof

:skip
	echo off
	call lib.bat :color Installation skipped
	call lib.bat :color INSTALL_LOCALLY=%INSTALL_LOCALLY%
	call lib.bat :color SKIP_JDK_INSTALL=%SKIP_JDK_INSTALL%
	echo on
	call lib.bat :ask
    goto :eof