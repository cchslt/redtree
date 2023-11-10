package com.stone.delaytask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @create 2022-07-24 22:19
 *
 * JDK延时队列
 *
 * 优点：效率高，延时率低
 *
 * 缺点：如服务宕机后重启，数据消失
 * 集群扩展麻烦
 * 内存原因，如延时数据较多，可能出现OOM
 * 代码复杂度高
 **/

public class JDKDelayQueueTask implements Delayed {
    private String orderId;

    private long timeout;

    public JDKDelayQueueTask(String orderId, long timeout) {
        this.orderId = orderId;
        this.timeout = timeout + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(timeout - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == this) {
            return 0;
        }

        JDKDelayQueueTask task = (JDKDelayQueueTask)o;
        long time = getDelay(TimeUnit.NANOSECONDS) - task.getDelay(TimeUnit.NANOSECONDS);
        return (time == 0) ? 0 : ((time < 0) ? -1 : 1);
    }

    void handle() {
        System.out.println("处理单号：" + orderId);
    }

    private final static DelayQueue<JDKDelayQueueTask> delayQueue = new DelayQueue();

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<String>();
        list.add("00000001");
        list.add("00000002");
        list.add("00000003");
        list.add("00000004");
        list.add("00000005");

        for (String orderId : list) {
            delayQueue.put(new JDKDelayQueueTask(orderId, TimeUnit.NANOSECONDS.convert(3, TimeUnit.SECONDS)));
            Thread.sleep(1000);
        }

        long start = System.currentTimeMillis();
        while (delayQueue.size() > 0) {
            try {

                delayQueue.take().handle();
                System.out.println("After " + (System.currentTimeMillis() - start) + " MilliSeconds");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
