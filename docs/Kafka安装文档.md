kafka 安装步骤
                  kafka安装文档

1、解压缩(官网下载:http://kafka.apache.org/downloads.html)

   tar -xzf kafka_2.10-0.8.2.0.tgz

   cd kafka_2.10-0.8.2.0

2、启动server服务(包括zookeeper服务、kafka服务)

   bin/zookeeper-server-start.sh  config/zookeeper.properties & (&表示在后台执行)

   ./kafka-server-start.sh ../config/server.properties &

3、创建topic

   bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

   ./kafka-topics.sh --create --zookeeper 192.168.45.52:2181/kafka --replication-factor 1 --partitions 1 --topic test

     查看topic命令:

     bin/kafka-topics.sh --list --zookeeper localhost:2181

4、发消息(producer发消息给kafka实例(broker)、consumer从kafka实例中接受数据)

  Producer: bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test

                   This is a message

                   This is another message

5、启动consumer

   bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning

   接受到的数据为:

    This is a message

     This is another message  注意：到这里单机kafka测试完成 下面介绍kafka集群安装与测试

6、首先准备3个虚拟机 安装zookeeper 3个节点的集群,分别为hadoop0、hadoop1、hadoop2 

   这里就不介绍了！！！！！！！

7、安装好zookeeper后 在3个节点分别执行 zkServer.sh start 

    查看状态命令为:zkServer.sh status

8、Hadoop0(192.168.80.100) 、Hadoop1(192.168.80.101) 、hadoop2(192.168.80.102) 对应主机   kafka下面的$KAFKA_HOME/conf/server.properties 分别修改文件为:

  Hadoop0：

        broker.id=0

        port=9092

        host.name=hadoop0

        advertised.host.name=hadoop0

        .....

        num.partitions=2

        zookeeper.contact=hadoop0:2181,hadoop1:2181,hadoop2:2181

 Hadoop1:

        broker.id=1

        port=9093

        host.name=hadoop1

        advertised.host.name=hadoop1

        .....

        num.partitions=2

        zookeeper.contact=hadoop0:2181,hadoop1:2181,hadoop2:2181

  Hadoop2：

        broker.id=2

        port=9094

        host.name=hadoop2

        advertised.host.name=hadoop2

        .....

        num.partitions=2

        zookeeper.contact=hadoop0:2181,hadoop1:2181,hadoop2:2181

  9、模拟测试:

     (1)分别在hadoop0、Hadoop1、hadoop2节点分别执行:

       $KAFKA_HOME/bin/kafka-server-start.sh  $KAFKA_HOME/config/server.properties &

     (2)在hadoop0新建topic  命令为：

      kafka-topics.sh --create --topic test --replication-factor 3 --partitions 2 --zookeeper hadoop0:2181  

      查看多少个topic命令：

            $KAFKA_HOME/bin/kafka-topics.sh --list --zookeeper hadoop0:2181 

      查看具体topic分区、备份信息(topic test001)命令:

        kafka-topics.sh    --describe --zookeeper hadoop0:2181 --topic test001

     (3)用hadoop1模拟producer(生产者)，发消息到kafka 命令为：

   kafka-console-producer.sh --broker-list hadoop0:9092 --sync --topic test

   发消息内容:  this is a message

              This is another message

     (4)用hadoop2模拟consumer(消费者) 命令为:

      kafka-console-consumer.sh --zookeeper hadoop0:2181 --topic test

       接收到的消息为:

       this is a message

           This is another message

 

     集群测试完毕！！！！

 

下面为topic的分区与复制

  1、创建debugo01,这个topic分区为3,复制为1(没有复制)。该topic夸越全部broker

   kafka-topics.sh  --create --zookeeper hadoop0,hadoop1,hadoop2 --replication-factor 1 --partition 3 --topic debugo01

  2、创建debugo02,这个topic分区为1，复制为3。(每一个主机都有一份).

   kafka-topics.sh  --create --zookeeper hadoop0,hadoop1,hadoop2 --replication-factor 3 --partition 1 --topic debugo02

   3、创建debugo03,这个topic分区为3,复制为2。

     kafka-topics.sh  --create --zookeeper hadoop0,hadoop1,hadoop2 --replication-factor 2 --partition 3 --topic debugo03

   查看debugo03的具体信息:

    kafka-topics.sh    --describe --zookeeper hadoop0:2181 --topic debugo03