call 0-settings.bat

echo off
call lib.bat :color Installing JDK...
echo on

if "%SKIP_JDK_INSTALL%"=="true" ( goto :skip )
if "%INSTALL_LOCALLY%"=="false" ( goto :skip )
if "%INSTALL_LOCALLY%"=="" ( goto :skip )

call :install jdk %ARCH_JDK% %ARCH_JDK_FOLDER%

call lib.bat :print_color %MVNW% -v

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

:install
    cd %ROOT%
    IF EXIST %TOOLS%\%~1.zip (
        del /Q %TOOLS%\%~1.zip
    )
    powershell -command "& { set-executionpolicy remotesigned -s currentuser; [System.Net.ServicePointManager]::SecurityProtocol = 3072 -bor 768 -bor 192 -bor 48; $client=New-Object System.Net.WebClient; $client.Headers.Add([System.Net.HttpRequestHeader]::Cookie, 'oraclelicense=accept-securebackup-cookie'); $client.DownloadFile('%~2','%TOOLS%\%~1.zip') }"
    rd /S /Q %TOOLS%\..\.%~1
    %ARCH% x -y -o%TOOLS%\.. %TOOLS%\%~1.zip
    rename %TOOLS%\..\%~3 .%~1
    cd %ROOT%
    goto :eof