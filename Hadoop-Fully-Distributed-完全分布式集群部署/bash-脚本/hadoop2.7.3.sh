#!/bin/sh
# Author:wangxiaolei 王小雷
# Blog:http://blog.csdn.net/dream_an
# Github:https://github.com/wxiaolei
# Date:20161027
# Path：/etc/profile.d/hadoop2.7.3.sh

export HADOOP_HOME="/opt/hadoop-2.7.3"
export PATH="$HADOOP_HOME/bin:$PATH"
export HADOOP_CONF_DIR=$HADOOP_HOME/etc/hadoop
export YARN_CONF_DIR=$HADOOP_HOME/etc/hadoop
