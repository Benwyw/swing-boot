@echo off
set "source=C:\Users\User\Downloads\temp.txt"
set "destination=C:\Users\User\Downloads\clone\temp.txt"

:: Attempt to copy the file
copy "%source%" "%destination%"

:: Check the errorlevel value to determine if the copy was successful
if %errorlevel% equ 0 (
    echo File has been copied successfully.
    exit /b 0
) else (
    echo Failed to copy file.
    exit /b 1
)
