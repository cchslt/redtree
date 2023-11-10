package com.stone.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author chen
 * @create 2023-11-09 00:28
 **/

public class MetaspaceOOm {

    static class Oom {

    }

    public static void main(final String[] args) {

        int i = 0;
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(Oom.class);
                enhancer.setUseCache(false);

                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });

                enhancer.create();

            }
        } catch (Throwable e) {
            System.out.println("=================多少次后发生异常:"+i);
            e.printStackTrace();
        }
    }

}
