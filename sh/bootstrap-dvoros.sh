#!/bin/bash

#Download sqoop
curl -s http://archive.apache.org/dist/sqoop/1.4.7/sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz | tar -xz -C /usr/local
ln -s /usr/local/sqoop-1.4.7.bin__hadoop-2.6.0 $SQOOP_HOME

#Update Path
PATH=$PATH:$HADOOP_HOME/bin:$SQOOP_HOME/bin
bash $HADOOP_HOME/etc/hadoop/hadoop-env.sh

#Remove pids
rm /tmp/*.pid

# installing libraries if any - (resource urls added comma separated to the ACP system variable)
cd $HADOOP_HOME/share/hadoop/common ; for cp in ${ACP//,/ }; do  echo == $cp; curl -LO $cp ; done; cd -

sed s/HOSTNAME/$HOSTNAME/ $HADOOP_HOME/etc/hadoop/core-site.xml.template > /usr/local/hadoop/etc/hadoop/core-site.xml

#Copy DB Connection
cp /jar/mysql-connector-java-5.1.38.jar /usr/local/sqoop/lib/
chmod 777 /usr/local/sqoop/lib/mysql-connector-java-5.1.38.jar

#/usr/sbin/sshd
bash $HADOOP_HOME/sbin/start-dfs.sh
bash $HADOOP_HOME/sbin/start-yarn.sh
bash $HADOOP_HOME/bin/mapred --daemon start historyserver
bash $HADOOP_HOME/bin/hdfs dfsadmin -safemode leave

/bin/bash