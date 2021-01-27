call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo RUNCRUD has errors
goto fail

:runbrowser
start chrome http://localhost:8080/crud/v1/task/getTasks

goto end

:fail
echo.
echo There was some error

:end
echo.
echo Work is done.