#!/bin/sh
# Author:wangxiaolei 王小雷
# Blog:http://blog.csdn.net/dream_an
# Github:https://github.com/wxiaolei
# Date:20161027
# Path:/etc/profile.d/jdk1.8.sh

export JAVA_HOME=/opt/jdk1.8.0_111
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH
