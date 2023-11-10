//package com.stone.spark.kafka.producer;
//
//import com.stone.spark.kafka.contant.KafkaPropertis;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.serialization.IntegerDeserializer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//
//import java.util.*;
//
//public class RedTreeConsumer extends Thread{
//
//    private String topic;
//
//    private KafkaConsumer consumer;
//
//    public RedTreeConsumer(String topic) {
//        this.topic = topic;
//
//        Properties props = new Properties();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaPropertis.BROKER_LIST);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaPropertis.GROUP_ID);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//        consumer = new KafkaConsumer<Integer, String>(props);
//    }
//
//
//    @Override
//    public void run() {
//
//        consumer.subscribe(Arrays.asList(topic));
//
//        while (true) {
//            ConsumerRecords<Integer, String> records = consumer.poll(100);
//            for (ConsumerRecord<Integer, String> record : records) {
//                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
//            }
//        }
//    }
//}
