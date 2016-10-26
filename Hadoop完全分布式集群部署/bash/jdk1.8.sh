#!/bin/sh
# author:wangxiaolei 王小雷
# blog:http://blog.csdn.net/dream_an
# date:20161026
# root:/etc/profile.d/jdk1.8.sh
export JAVA_HOME=/opt/jdk1.8.0_111
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH
