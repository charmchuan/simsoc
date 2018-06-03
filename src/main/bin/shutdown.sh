#!/bin/bash

DIR_NAME=`dirname $0`
ROOT_DIR=`cd ${DIR_NAME}/.. && pwd`

pid=`cat ${ROOT_DIR}/running.pid`
kill -9 ${pid}
echo "------ Application [${pid}] is shut down successfully ------"
rm -f ${ROOT_DIR}/running.pid
