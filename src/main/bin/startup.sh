#!/bin/bash

DIR_NAME=`dirname $0`
ROOT_DIR=`cd ${DIR_NAME}/.. && pwd`

nohup java -jar ${ROOT_DIR}/simsoc-0.0.1-SNAPSHOT.jar --spring.config.location=${ROOT_DIR}/config/application.properties --root_dir=${ROOT_DIR} > /dev/null 2>&1 &

pid=$!
echo ${pid} > ${ROOT_DIR}/running.pid

echo "------ Application [${pid}] is started up successfully ------"
