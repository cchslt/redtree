package com.stone.delaytask;

import com.google.common.collect.Lists;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @create 2022-07-24 22:50
 *
 * 基于Netty的时间轮询的延时
 *
 * 优点：代码复杂度低、效率高
 *
 *缺点: 同JDK延时队列
 *
 **/

public class HashedWheelTimerTask {
    static Timer timer = new HashedWheelTimer();
    static List<MyTask> taskList = Lists.newLinkedList();

    static class MyTask implements TimerTask {

        boolean flag;

        public MyTask(boolean flag) {
            taskList.add(this);
            this.flag = flag;
        }

        @Override
        public void run(Timeout timeout) throws Exception {
            System.out.println("时间到，执行任务去了");
            taskList.remove(this);
            flag = false;
        }
    }


    public static void main(String[] args) {
        MyTask task = new MyTask(true);
        timer.newTimeout(task, 5, TimeUnit.SECONDS);

        MyTask task1 = new MyTask(true);
        timer.newTimeout(task1, 9, TimeUnit.SECONDS);

        int i = 1;
        while (taskList.size() > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + "秒过去了" + " taskList size :" + taskList.size());
            i++;
        }
    }


}
