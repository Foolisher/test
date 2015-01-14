@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\io\terminus\terminus-common\1.2-SNAPSHOT\terminus-common-1.2-20150105.134339-11.jar;"%REPO%"\redis\clients\jedis\2.5.2\jedis-2.5.2.jar;"%REPO%"\org\apache\commons\commons-pool2\2.0\commons-pool2-2.0.jar;"%REPO%"\org\slf4j\slf4j-api\1.7.5\slf4j-api-1.7.5.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-databind\2.4.2\jackson-databind-2.4.2.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-annotations\2.4.0\jackson-annotations-2.4.0.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-core\2.4.2\jackson-core-2.4.2.jar;"%REPO%"\com\fasterxml\jackson\datatype\jackson-datatype-guava\2.4.2\jackson-datatype-guava-2.4.2.jar;"%REPO%"\net\sf\dozer\dozer\5.4.0\dozer-5.4.0.jar;"%REPO%"\org\slf4j\slf4j-log4j12\1.6.6\slf4j-log4j12-1.6.6.jar;"%REPO%"\org\aspectj\aspectjrt\1.7.0\aspectjrt-1.7.0.jar;"%REPO%"\org\aspectj\aspectjweaver\1.7.0\aspectjweaver-1.7.0.jar;"%REPO%"\org\mybatis\mybatis\3.2.3\mybatis-3.2.3.jar;"%REPO%"\org\freemarker\freemarker\2.3.9\freemarker-2.3.9.jar;"%REPO%"\mysql\mysql-connector-java\5.1.29\mysql-connector-java-5.1.29.jar;"%REPO%"\joda-time\joda-time\2.3\joda-time-2.3.jar;"%REPO%"\org\mybatis\mybatis-spring\1.1.1\mybatis-spring-1.1.1.jar;"%REPO%"\org\springframework\spring-core\3.1.1.RELEASE\spring-core-3.1.1.RELEASE.jar;"%REPO%"\org\springframework\spring-tx\3.1.1.RELEASE\spring-tx-3.1.1.RELEASE.jar;"%REPO%"\org\springframework\spring-beans\3.1.1.RELEASE\spring-beans-3.1.1.RELEASE.jar;"%REPO%"\org\springframework\spring-jdbc\3.1.1.RELEASE\spring-jdbc-3.1.1.RELEASE.jar;"%REPO%"\org\springframework\spring-context\3.1.1.RELEASE\spring-context-3.1.1.RELEASE.jar;"%REPO%"\org\springframework\spring-aop\3.1.1.RELEASE\spring-aop-3.1.1.RELEASE.jar;"%REPO%"\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;"%REPO%"\org\springframework\spring-expression\3.1.1.RELEASE\spring-expression-3.1.1.RELEASE.jar;"%REPO%"\org\springframework\spring-asm\3.1.1.RELEASE\spring-asm-3.1.1.RELEASE.jar;"%REPO%"\org\mockito\mockito-all\1.9.5\mockito-all-1.9.5.jar;"%REPO%"\org\springframework\spring-test\3.2.6.RELEASE\spring-test-3.2.6.RELEASE.jar;"%REPO%"\commons-dbcp\commons-dbcp\1.4\commons-dbcp-1.4.jar;"%REPO%"\commons-pool\commons-pool\1.5.4\commons-pool-1.5.4.jar;"%REPO%"\cglib\cglib\2.1_3\cglib-2.1_3.jar;"%REPO%"\asm\asm\1.5.3\asm-1.5.3.jar;"%REPO%"\org\projectlombok\lombok\1.12.4\lombok-1.12.4.jar;"%REPO%"\org\apache\commons\commons-lang3\3.1\commons-lang3-3.1.jar;"%REPO%"\com\google\guava\guava\18.0\guava-18.0.jar;"%REPO%"\junit\junit\4.11\junit-4.11.jar;"%REPO%"\org\apache\zookeeper\zookeeper\3.4.6\zookeeper-3.4.6.jar;"%REPO%"\log4j\log4j\1.2.16\log4j-1.2.16.jar;"%REPO%"\jline\jline\0.9.94\jline-0.9.94.jar;"%REPO%"\io\netty\netty\3.7.0.Final\netty-3.7.0.Final.jar;"%REPO%"\com\fasterxml\jackson\core\com.springsource.com.fasterxml.jackson.core.jackson-core\2.0.2\com.springsource.com.fasterxml.jackson.core.jackson-core-2.0.2.jar;"%REPO%"\com\github\kevinsawicki\http-request\5.6\http-request-5.6.jar;"%REPO%"\org\springframework\data\spring-data-redis\1.1.1.RELEASE\spring-data-redis-1.1.1.RELEASE.jar;"%REPO%"\org\slf4j\jcl-over-slf4j\1.6.6\jcl-over-slf4j-1.6.6.jar;"%REPO%"\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;"%REPO%"\commons-beanutils\commons-beanutils\1.8.3\commons-beanutils-1.8.3.jar;"%REPO%"\commons-logging\commons-logging\1.1.1\commons-logging-1.1.1.jar;"%REPO%"\ch\qos\logback\logback-classic\1.0.13\logback-classic-1.0.13.jar;"%REPO%"\ch\qos\logback\logback-core\1.0.13\logback-core-1.0.13.jar;"%REPO%"\terminus\test.mybator\0.0.1-SNAPSHOT\test.mybator-0.0.1-SNAPSHOT.jar

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS%  -classpath %CLASSPATH% -Dapp.name="mybator3" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" test.mybator.generator._19_ecp_wechatpay_trans %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
