package com.stone.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author chen
 * @create 2023-11-10 23:21
 **/

public class ObjectTest {

    public static void main(String[] args) {

        String str = new String();
        String string = ClassLayout.parseInstance(str).toPrintable();
//        System.out.println(string);


        test t = new test();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
    }

    static class test {
        private long a;
        private long b;
        private long c;
        private byte d;
    }
}
