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
