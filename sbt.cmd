set SCRIPT_DIR=%~dp0

title SBT: wicketscala

REM For example: set SBT_OPTS=-Dhttp.proxyHost=172.22.100.15 -Dhttp.proxyPort=8080
set SBT_OPTS=-Dhttp.proxyHost=172.22.100.15 -Dhttp.proxyPort=8080

REM JRebel
REMset JREBEL_OPTS=-noverify -javaagent:project/jrebel/jrebel.jar

java %SBT_OPTS% %JREBEL_OPTS% -Dfile.encoding=UTF-8 -Xmx512M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256M -jar "%SCRIPT_DIR%sbt-launch-0.7.4.jar" %*
