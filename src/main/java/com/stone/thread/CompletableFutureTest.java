package com.stone.thread;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author chen
 * @create 2019-11-06 23:13
 **/

public class CompletableFutureTest {

    public static void main(String[] args) {
        System.out.println("开始执行： " + new Date());
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> method1(10));
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> method2());
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> method3("down"));
        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> method4());

        try {
            System.out.println("future1 -> " + future1.get());
            System.out.println("future2 -> " + future2.get());
            System.out.println("future3 -> " + future3.get());
            System.out.println("future4 -> " + future4.get());
            System.out.println("future1 + future4 -> " + (future1.get() + future4.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("多线程执行需要的时间：" + (end - start));
        System.out.println("时间： " + new Date());
        System.out.println(method1(10));
        System.out.println(method2());
        System.out.println(method3("down"));
        long end2 = System.currentTimeMillis();
        System.out.println("正常顺序执行的时间：" + (end2 - end));
        System.out.println("结束时间： " + new Date());


    }

    private static Integer method1(Integer value) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value + 1;
    }

    private static String method2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "value: method2";
    }

    private static String method3(String value)  {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "execute: method3 ->" + value;
    }

    private static Integer method4()  {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 9;
    }
}
