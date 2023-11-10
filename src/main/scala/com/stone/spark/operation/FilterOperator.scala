package com.stone.spark.operation

//import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object FilterOperator {

  def main(args: Array[String]): Unit = {
    var sparkConf = new SparkConf().setAppName("FilterOperator").setMaster("local")
    var sc = new SparkContext(sparkConf)


    val numbers = List(1, 2, 3, 4, 5)
    var numRdd = sc.parallelize(numbers)
        .filter(f => f%2 ==0)


    numRdd.foreach(f => println("偶数为: " + f))
  }
}
