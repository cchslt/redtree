package com.stone.jvm;

/**
 * @author chen
 * @create 2020-07-15 23:00
 **/

public class JvmTest2 {

    public static void main(String[] args) {
        /**
         * -XX:NewSize=10M -XX:MaxNewSize=10M -XX:InitialHeapSize=20M -XX:MaxHeapSize=20M -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=10M -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
         *
         *
         */
        byte[] a1 = new byte[2 * 1024 * 1024];
        a1 = new byte[2 * 1024 * 1024];
        a1 = new byte[2 * 1024 * 1024];
        a1 = null;

        byte[] a2 = new byte[128 * 1024];

        byte[] a3 = new byte[2 * 1024 * 1024];

        a3 = new byte[2 * 1024 * 1024];
        a3 = new byte[2 * 1024 * 1024];
        a3 = new byte[128 * 1024];
        a3 = null;

        byte[] a4 = new byte[2 * 1024 * 1024];
    }
}
