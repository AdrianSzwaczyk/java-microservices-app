@echo off

@REM echo Running to_jar.bat...
@REM call to_jar.bat
@REM if %ERRORLEVEL% neq 0 (
@REM     echo Error: to_jar.bat failed. Exiting.
@REM     pause
@REM     exit /b %ERRORLEVEL%
@REM )

echo Running docker.ps1...
powershell -NoProfile -ExecutionPolicy Bypass -File docker.ps1
if %ERRORLEVEL% neq 0 (
    echo Error: docker.ps1 failed. Exiting.
    pause
    exit /b %ERRORLEVEL%
)

echo Both scripts executed successfully.
pause
