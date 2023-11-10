package com.stone.spark.Streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * author chen
  * create 2018-12-11 下午11:36
  * spark Streaming整合flume的第二种方式
  * 实际生产环境中，一般采用此种方式
  *
  * 1)、启动streaming作业
  * 2）、启动flume-ng agent： flume-ng agent --name simple-agent --conf $FLUME_HOME/conf --conf-file $F_HOME/conf/flume_poll_streaming.conf -Dflume.root.logger=INFO,console
  * 3)、telnet localhost 44444 输入数据
  *
  * 问题： 启动flume agent时报：java.lang.IllegalStateException: begin() called when transaction is OPEN!
  * 解决方法: scala-library 包出现冲突，将pom文件中的scala包替换掉$lume_home lib下的scala包
  *
  **/

object FlumePollWordCount{

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("FlumePollWordCount")
    val scc = new StreamingContext(sparkConf, Seconds(5))

    if (args.length < 2) {
      System.err.println("Usage: 需要传入host和端口号: <hostname> <port>")
    }
    val Array(hostname, port) = args

    val flumeStream = FlumeUtils.createPollingStream(scc, hostname, port.toInt);
    flumeStream.map(x => new String(x.event.getBody.array()).trim)
        .flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).print()

    scc.start()
    scc.awaitTermination()

  }



}
