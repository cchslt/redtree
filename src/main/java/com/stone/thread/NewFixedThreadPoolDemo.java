package com.stone.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chen
 * @create 2021-03-07 00:03
 **/

public class NewFixedThreadPoolDemo {
    private static Logger logger = LoggerFactory.getLogger(NewFixedThreadPoolDemo.class);


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i< 5; i++) {
            int group = i;

            executorService.execute(() -> {
                for (int j = 0; j< 5; j++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }

                    logger.info("第 " + group + "组, 第 " + j +  " 次任务!!");
                }
            });
        }

        executorService.shutdownNow();
    }
}
