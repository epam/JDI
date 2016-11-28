for /D /R  %%D IN (.logs) DO @if exist "%%D" rmdir /S /Q "%%D"
pause