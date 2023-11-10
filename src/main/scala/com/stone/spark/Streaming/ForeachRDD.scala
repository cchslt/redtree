package com.stone.spark.Streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object ForeachRDD {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setAppName("ForeachRDD").setMaster("local[2]")
    val scc = new StreamingContext(sparkConf, Seconds(5))

    val lines = scc.socketTextStream("localhost", 8888);
    val result = lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_)

//    result.foreachRDD(rdd => {
//      rdd.foreachPartition(records => {
//        println(records)
//      })
//    })
    result.print()
    scc.start()
    scc.awaitTermination()

  }

}
