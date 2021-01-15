#!/bin/sh
#对变量赋值：
str=`lsof -i:$1`
# 现在打印变量a的内容：
if [[ $str != "" ]]
then
    
    i=1
while((1==1))
do
  splitchar=`echo $str|cut -d " " -f$i`
  if [ "$splitchar" != "" ]
  then
      ((i++))
      if [ "$i" == 12 ]
      then
        echo $splitchar
        kill -9 $splitchar
      fi
      
  else
      break
  fi
done
    # echo "$a"
    # nohup java -jar ./jeecg-boot-module-system-2.4.0.jar --server.port=7002 &
fi