package com.stone.spark.Streaming

import org.apache.commons.codec.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.KafkaUtils
//import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * author chen
  * create 2018-12-12 下午11:27
  *
  * Spark Streaming对接kafka的第一种方式
  **/

object KafkaReceiverWordCount {

  def main(args: Array[String]): Unit = {
    if (args.length != 4) {
      System.err.println("Usage : 传入参数必须为4个参数: <zkQuorum>, <groupId>, <topics>, <numThreads>")
      System.exit(1)
    }
    val Array(zkQuorum, groupId, topics, numThreads) = args

    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("KafkaReceiverWordCount")
    val scc = new StreamingContext(sparkConf, Seconds(5))

    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap


    //Spark Streaming对接kafka
    val msg = KafkaUtils.createStream(scc, zkQuorum, groupId, topicMap)


    msg.map(_._2).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).print()

    scc.start()
    scc.awaitTermination()
  }

}
