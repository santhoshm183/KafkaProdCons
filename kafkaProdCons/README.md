

----Overview-------

Kafka Example - produce consumer examples

This Example demostrate how we can produce and consumes message using kafka.

First we need to install Kafka with Zookeeper

Than we need to exutee below cmd

Run Zookeeper in cam prompt 
E:\kafka_2.11-2.4.1>.\bin\windows\zookeeper-server-start.bat config\zookeeper.properties
It start with port 2181, you can check the confirmation in config\zookeeper.properties

You can check it stared not using telnet cmd
C:\Users\sanju>telnet localhost 2181

Will start single Kafka broker 
E:\kafka_2.11-2.4.1>.\bin\windows\kafka-server-start.bat config\server.properties
It start with 9092 port

Will create a topic 
E:\kafka_2.11-2.4.1>.\bin\windows\kafka-topics.bat --create --topic my_topic --zookeeper localhost:2181 --replication-factor 1 --partitions 1


