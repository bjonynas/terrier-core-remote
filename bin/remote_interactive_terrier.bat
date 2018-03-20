@echo off

if "Windows_NT"=="%OS%" setlocal

rem keep %0 in case we overwrite
SET PROGRAM=%0
rem SCRIPT contains the full path filename of this script
SET SCRIPT=%~f0
rem BIN contains the path of the BIN folder
SET BIN=%~dp0


REM --------------------------
REM Load a settings batch file if it exists
REM --------------------------
if NOT EXIST "%BIN%\terrier-env.bat" GOTO defaultvars
CALL "%BIN%\terrier-env.bat" "%BIN%\.."

:defaultvars

set COLLECTIONPATH=%~f1

REM --------------------------
REM Derive TERRIER_HOME, then call anyclass.bat
REM --------------------------

if defined TERRIER_HOME goto run_anyclass
CALL "%BIN%\fq.bat" "%BIN%\.."
SET TERRIER_HOME=%FQ%

:run_anyclass
%TERRIER_HOME%\bin\anyclass.bat org.terrier.remote_client.RemoteInteractiveQuerying %*

if "Windows_NT"=="%OS%" endlocal

