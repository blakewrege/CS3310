@echo off
echo Trys to pull then push to github
git pull
git add *
set /p gitcomment= Enter Comment: 
git commit -m %gitcomment%
git push origan master
echo Done
pause