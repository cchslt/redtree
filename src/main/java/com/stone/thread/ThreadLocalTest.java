package com.stone.thread;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author chen
 * @create 2022-01-12 17:24
 **/

public class ThreadLocalTest {

    /**
     * 单例
     */
    private static final ThreadLocalTest INSTANCE = new ThreadLocalTest();

    private ThreadLocalTest() {

    }


    public static ThreadLocalTest getInstance() {
        return INSTANCE;
    }


    private static final ThreadLocal<SecureRandom> SECURE_RANDOM_THREAD_LOCAL = new ThreadLocal<SecureRandom>() {
        @Override
        protected SecureRandom initialValue() {
            SecureRandom secureRandom = null;
            try {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                secureRandom = new SecureRandom();
            }

            return secureRandom;
        }
    };

    public int nextInt(int bound) {
        SecureRandom random = SECURE_RANDOM_THREAD_LOCAL.get();
        return random.nextInt(bound);
    }

}
