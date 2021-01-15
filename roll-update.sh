#!/bin/sh
#对变量赋值：
a=`lsof -i:7001`
# 现在打印变量a的内容：
if [[ $a == *java* ]]
then
    echo "7001端口被占用，在7002端口启动新服务"
    nohup java -jar ./jeecg-boot-module-system-2.4.0.jar --server.port=7002 &
else
    echo "7001端口未被占用，在7001端口启动新服务"
    nohup java -jar ./jeecg-boot-module-system-2.4.0.jar --server.port=7001 &
fi

# echo $a