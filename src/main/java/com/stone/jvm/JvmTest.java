package com.stone.jvm;

/**
 * @author chen
 * @create 2020-07-15 22:40
 **/

public class JvmTest {

    public static void main(String[] args) {
        /**
         * 设置jvm参数
         * -XX:NewSize=5242880 -XX:MaxNewSize=5242880
         * -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760
         * -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=10485760
         * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
         * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
         * -Xloggc:gc.log
         *
         *
         * 模拟发生Minor GC的场景，并打印日志
         */
        byte[] a1 = new byte[1024 * 1024];
        a1 = new byte[1024 * 1024];
        a1 = new byte[1024 * 1024];
        a1 = null;

        byte[] a2 = new byte[2 * 1024 * 1024];
    }
}
