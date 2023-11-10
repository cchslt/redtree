package com.stone.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chen
 * @create 2021-03-06 22:52
 **/

public class ThreadPoolDemo implements Executor {

    private final AtomicInteger ctl = new AtomicInteger(0);

    /**
     * 线程数
     */
    private volatile int corePoolSize;

    /**
     * 最大线程数
     */
    private volatile int maxPoolSize;

    /**
     * 待执行线程队列
     */
    private final BlockingQueue<Runnable> workQueue;

    public ThreadPoolDemo(int corePoolSize, int maxPoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.workQueue = workQueue;
    }


    @Override
    public void execute(Runnable command) {

        int c = ctl.get();
        if (c < corePoolSize) {
            if (!addWorker(command)) {
                reject();
            }
            return;
        }


        if (!workQueue.offer(command)) {
            if (!addWorker(command)) {
                reject();
            }
        }
    }

    /**
     * 将线程添加到待执行的队列中
     * @return
     */
    private boolean addWorker(Runnable runnable) {

        if (ctl.get() >= maxPoolSize) {
            return false;
        }

        Worker worker = new Worker(runnable);
        worker.thread.start();
        //线程基数+1
        ctl.incrementAndGet();

        return true;
    }


    /**
     * 工作线程
     */
    private final class Worker implements Runnable{

        final Thread thread;
        Runnable firstTask;

        public Worker(Runnable firstTask) {
            this.thread = new Thread(this);
            this.firstTask = firstTask;
        }


        @Override
        public void run() {
            Runnable task = firstTask;

            try {

                while (task != null || (task = getTask()) != null) {
                    task.run();

                    if (ctl.get() > maxPoolSize) {
                        break;
                    }
                    task = null;
                }
            }catch (Exception e) {

            } finally {
                ctl.decrementAndGet();
            }

        }



        /**
         * 获取队列中的线程
         * @return
         */
        private Runnable getTask() {
            for (;;) {
                try {
                    System.out.println("WokerQueue size:" + workQueue.size());

                    return workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 当线程队列中的已满，主线程的策略
     */
    private void reject() {
        throw new RuntimeException("Error!! ctl size: " + ctl.get() + " workQueue size: " + workQueue.size());
    }


    public static void main(String[] args) {
        ThreadPoolDemo demo = new ThreadPoolDemo(2, 2, new ArrayBlockingQueue<>(5));


        for (int i = 0; i < 50; i++) {
            int cc = i;
            demo.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务编号: " + cc);
            });
        }
    }
}


