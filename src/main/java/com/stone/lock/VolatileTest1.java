package com.stone.lock;

/**
 * @author chen
 * @create 2021-03-03 07:17
 **/

public class VolatileTest1 {

    public static void main(String[] args) {

        /**
         * volatile修饰的变量存在主内存中，并将复制到各个线程使用的CPU临时内存中
         * 1）、当没有volatile修饰时，线程t2修改ti的sign值，只是修改CPU中的值，不会刷新到主内存中，因此t1不会获取到修改后的sign
         * 2）、volatile修饰变量是，会将sign CPU中修改后的值强制刷新到主内存中，t1使用sign值时，
         *      会把自己内存中的sign值设置过期，再从主内存中获取最新的值
         */
        final VT vt = new VT();
        Thread t1 = new Thread(vt);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                vt.sign = true;
                System.out.println("兄弟，我走了,到你了 ");
            }
        });


        t1.start();
        t2.start();
    }

}

class VT implements Runnable{


    public boolean sign = false;
//    public volatile boolean sign = false;

    @Override
    public void run() {

        while (!sign) {
            /**
             * 添加一段代码，没有volatile修饰时，jvm也尽量保证其可见性
             */
            System.out.println("我出不来了");
        }
        System.out.println("我摊牌了！！");
    }


}
