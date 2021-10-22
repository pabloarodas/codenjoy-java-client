call %*
goto :eof

:print_color
	call %* > %TOOLS%\out
	echo off
    for /f "tokens=*" %%s in (%TOOLS%\out) do (
         call :color %%s
    )
    echo on
    del /Q %TOOLS%\out
    goto :eof

:color
    echo [44;93m%*[0m
    goto :eof

:ask
    echo off
    call :color Press any key to continue
    echo on
    pause >nul
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