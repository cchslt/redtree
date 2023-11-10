package com.stone.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @create 2022-01-12 17:40
 **/

public class UserPasswordSysManage {

    private static final UserPasswordSysManage INSTANCE = new UserPasswordSysManage();
    private UserPasswordSysManage() {

    }
    public static UserPasswordSysManage getInstance() {
        return INSTANCE;

    }


    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(1,
            Runtime.getRuntime().availableProcessors() * 2,
            60,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            r -> {
                Thread thread = new Thread(r, "rigiste-thread-");
                thread.setDaemon(true);
                return thread;
            }
    );


//    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(3);

    public void register(String userName, String phoneNum) {
        Runnable task = () -> {
            ThreadLocalTest threadLocalTest = ThreadLocalTest.getInstance();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <8; i++) {
                sb.append(threadLocalTest.nextInt(10));
            }
            String initPassword = sb.toString();

            saveUser(userName, phoneNum, initPassword);
            sendMsg(phoneNum, initPassword);
        };

        EXECUTOR.submit(task);

    }


    private void saveUser(String loginUserName, String phoneNum, String initPassword) {
        System.out.println("账号： " +  loginUserName + "   电话号码： " + phoneNum + " 初始密码: " + initPassword);
    }


    private void sendMsg(String phoneNum,  String initPassword) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发送短信，用户完成注册,手机号码： " + phoneNum + "  初始密码: " + initPassword);
    }


    public static void main(String[] args) throws InterruptedException {
        UserPasswordSysManage instance = UserPasswordSysManage.getInstance();
        instance.register("zhangsan1", "13900008885");
        instance.register("zhangsan2", "13900008886");
        instance.register("zhangsan3", "13900008887");
        instance.register("zhangsan4", "13900008888");

        Thread.sleep(100);
    }
}
