package com.stone.thread;

public class Demo extends Thread {

    public Demo(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + " 线程开始执行了");
    }


    public static void main(String[] args) {
        Demo demo1 = new Demo("fisrt blood");
        Demo demo2 = new Demo("second blood");

        demo1.start();
        demo2.start();
    }
}
