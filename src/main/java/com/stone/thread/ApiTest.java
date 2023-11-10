package com.stone.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @create 2021-03-05 17:31
 **/

public class ApiTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        executor.execute(() -> System.out.println("HI~~~~"));
        executor.shutdown();
    }
}
