package com.seamtop.demo.kafka;
/**
 */
public class TestMain
{
    public static void main(String[] args)
    {
        KafkaProducer producerThread = new KafkaProducer(KafkaProperties.topic);
        producerThread.start();
//        KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.topic);
//        consumerThread.start();
    }
}