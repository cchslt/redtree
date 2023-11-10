package com.stone.spark.kafka.producer;


import com.stone.spark.kafka.contant.KafkaPropertis;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;


/**
 *
 * kafka生产者
 *
 */
public class RedTreeProducer extends Thread{

    private String topic;

    private KafkaProducer producer;

    public RedTreeProducer(String topic) {
        this.topic = topic;

        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaPropertis.BROKER_LIST);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("acks", "1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<Integer, String>(props);
    }


    @Override
    public void run() {
        int msgNum = 1;
        while (true) {
            String msg = "hi___" + msgNum;

            producer.send(new ProducerRecord<Integer, String>(topic, msg));
            System.out.println("Send:  " + msg);

            msgNum ++;

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
