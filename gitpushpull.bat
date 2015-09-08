@echo off
echo Trys to pull then push to github
git pull origin master
git add -A
set /p gitcomment= Enter Comment: 
git commit -m %gitcomment%
git push origin master
echo Done
pause