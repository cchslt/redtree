package com.stone.spark.Streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * author chen
  * create 2018-12-10 下午11:36
  * spark Streaming整合flume的第一种方式
  *
  * 1)、启动streaming作业
  * 2）、启动flume-ng agent： flume-ng agent --name simple-agent --conf $FLUME_HOME/conf --conf-file $F_HOME/conf/flume_push_streaming.conf -Dflume.root.logger=INFO,console
  * 3)、telnet localhost 44444 输入数据
  *
  **/

object FlumePushWordCount {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("FlumePushWordCount")
    val scc = new StreamingContext(sparkConf, Seconds(5))

    if (args.length < 2) {
      System.err.println("Usage: 需要传入host和端口号: <hostname> <port>")
    }
    val Array(hostname, port) = args

    val flumeStream = FlumeUtils.createStream(scc, hostname, port.toInt);
    flumeStream.map(x => new String(x.event.getBody.array()).trim)
        .flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).print()

    scc.start()
    scc.awaitTermination()

  }



}
