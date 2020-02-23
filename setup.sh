#!/bin/bash

date=$(date "+%Y%m%d")
prefix="/logistics-soure-"
dirName=$prefix$date
absolutePath=$(pwd)
mkdir "$absolutePath""$dirName"
cd "$absolutePath""$dirName" || exit
git init
git pull https://github.com/chenzeshenga/logistics-biu.git
cd "$absolutePath""$dirName""/outer-dist/" || exit
mvn install:install-file -Dfile=MWSOrders_2013-09-01_v2018-10-31.jar  -DgroupId=com.amazonservices.mws  -DartifactId=MWSOrders -Dversion=1.0  -Dpackaging=jar
mvn install:install-file -Dfile=MWSClientJavaRuntime-1.0.jar  -DgroupId=com.amazonservices.mws  -DartifactId=MWSClientJavaRuntime -Dversion=1.0  -Dpackaging=jar
cd "$absolutePath""$dirName""/server/" || exit
mvn clean package
cd "$absolutePath""$dirName""/server/target" || exit
cp ./server-1.0.jar /opt/biu
ps -ef | grep server-1.0.jar | grep -v grep | awk '{print $2}' | xargs kill -9
chmod -R 755 /opt/biu/server-1.0.jar
cd /opt/biu || exit
nohup java -jar server-1.0.jar
