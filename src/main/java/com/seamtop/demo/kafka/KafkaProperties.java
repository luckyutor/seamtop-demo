package com.seamtop.demo.kafka;

public interface KafkaProperties
{
    final static String zkConnect = "192.168.126.130:2181";
    final static String groupId = "group1";
    final static String topic = "test";
    final static String kafkaServerURL = "192.168.126.130";
    final static int kafkaServerPort = 9092;
    final static int kafkaProducerBufferSize = 64 * 1024;
    final static int connectionTimeOut = 20000;
    final static int reconnectInterval = 10000;
    final static String topic2 = "test";
    final static String topic3 = "test";
    final static String clientId = "SimpleConsumerDemoClient";
}