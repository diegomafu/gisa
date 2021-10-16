#!/bin/bash

#Update
apt-get update && apt-get install -y unzip wget && rm -rf /var/lib

#Download sqoop
wget -qO- http://archive.apache.org/dist/sqoop/1.4.7/sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz | tar xvz && mv sqoop-1.4.7.bin__hadoop-2.6.0 sqoop
#ln -s /usr/local/sqoop-1.4.7.bin__hadoop-2.6.0 $SQOOP_HOME

#Update Path
PATH=$PATH:${SQOOP_HOME}/bin

wget https://repo1.maven.org/maven2/commons-lang/commons-lang/2.6/commons-lang-2.6.jar && mv commons-lang-2.6.jar $SQOOP_HOME/lib

#Copy DB Connection
cp /jar/mysql-connector-java-5.1.38.jar ${SQOOP_HOME}/lib/
chmod 777 ${SQOOP_HOME}/lib/mysql-connector-java-5.1.38.jar

mv $SQOOP_HOME/conf/sqoop-env-template.sh $SQOOP_HOME/conf/sqoop-env.sh

echo "export HADOOP_COMMON_HOME=/hadoop-3.3.1" >> $SQOOP_HOME/conf/sqoop-env.sh

echo "export HADOOP_MAPRED_HOME=/hadoop-3.3.1" >> $SQOOP_HOME/conf/sqoop-env.sh

cd /

# Boot Hadoop services.
# (This also leaves user with the shell.)
./bootstrap.sh