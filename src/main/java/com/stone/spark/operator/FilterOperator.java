package com.stone.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

public class FilterOperator {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("FilterOperator").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        JavaRDD<Integer> filterRDD =  sc.parallelize(numbers)
                .filter(new Function<Integer, Boolean>() {
                    public Boolean call(Integer v1) throws Exception {
                        return v1 % 2==0;
                    }
                });

        filterRDD.foreach(new VoidFunction<Integer>() {
//            @Override
            public void call(Integer integer) throws Exception {
                System.out.println("整数: " + integer);
            }
        });
    }
}
