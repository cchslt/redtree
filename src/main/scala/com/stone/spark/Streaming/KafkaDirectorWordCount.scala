package com.stone.spark.Streaming

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * author chen
  * create 2018-12-12 下午11:27
  *
  * Spark Streaming对接kafka的第二种方式，直接从kafka队列中获取数据
  *
  * 较Receiver方式的优点：
  *  1、简化并行: 创建于kafka分区一样多的RDD分区，并行得从kafka读取，kafka与RDD之间存在一对一的映射
  *  2、提高效率: 第一种方法中实现零数据丢失需要将数据存储在预写日志中,会进行两次的数据复制，第一次是kafka的复制，第二次是 Write-Ahead-Log复制
  *             Director没有接收器，所以不需要预写日志，只要在kafka中保留数据，就可以直接从kafka中回复消息
  *  3、Exactly-once semantics： 第一种方法使用Kafka的高级API在Zookeeper中存储消耗的偏移量，但在某些故障情况下，某些记录可能会被消耗两次，Spark Streaming可靠接收的数据与Zookeeper跟踪的偏移之间存在不一致
  *             Director不使用Zookeeper，Spark Streaming在其检查点内跟踪偏移量
  *
  * Director缺点：不会更新Zookeeper中的偏移量，因此基于Zookeeper的Kafka监视工具将不会显示进度
  * 解决方案: 可以在每个批处理中访问此方法处理的偏移量，并自行更新Zookeeper
  **/

object KafkaDirectorWordCount {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("KafkaDirectorWordCount")
    val scc = new StreamingContext(sparkConf, Seconds(5))

    val kafkaParams = Map[String,String]("metadata.broker.list"->"localhost:9092")
    val topicsSet = Set("hello_topic")


    //Spark Streaming对接kafka
    val msg = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](scc, kafkaParams, topicsSet)


    msg.map(_._2).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).print()

    scc.start()
    scc.awaitTermination()
  }

}
