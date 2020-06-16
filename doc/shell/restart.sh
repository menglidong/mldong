#!/bin/bash
jar_name=mldong-admin.jar
jar_path=/java_projects/mldong-admin
jar_pid=$(ps -ef | grep $jar_path/$jar_name | grep -v grep | awk '{print $2}')
echo "$jar_name PID $jar_pid"
if [ -n "$jar_pid" ]
then
	#!kill -9 强制终止
	   echo "kill -9 强制终止" $jar_pid
	      kill -9 $jar_pid
      fi

nohup java -server -Xms512m -Xmx512m  -jar $jar_path/$jar_name --server.port=18081 --spring.profiles.active=test > $jar_path/nohub.out &
      echo "**********************$jar_name*************************"