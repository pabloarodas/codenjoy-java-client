call %*
goto :eof

:print_color
	call %* > %TOOLS%\out
    echo off
    for /f "tokens=*" %%s in (%TOOLS%\out) do (
        echo        [44;93m%%s[0m
    )
    echo on
    del /Q %TOOLS%\out
    goto :eof

:ask
    echo off
    echo        [44;93m+---------------------------------+[0m
    echo        [44;93m!    Press any key to continue    ![0m
    echo        [44;93m+---------------------------------+[0m
    echo on
    pause >nul
    goto :eof
