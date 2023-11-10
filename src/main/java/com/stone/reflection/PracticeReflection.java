package com.stone.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author chen
 * @create 2019-11-12 22:39
 **/

public class PracticeReflection {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        //1、获取class对象
        Class teacherClass = Class.forName("com.stone.reflection.Teacher");


        //2、获取声明的构造函数，多个参数，以'，'分开
        Constructor teatherConstruct = teacherClass.getDeclaredConstructor(String.class);
        //私有方式需要设置为可以实例化
        teatherConstruct.setAccessible(true);
        //使用构造方法的newInstance创建对象，参入构造函数需要的参数
        Object teacher = teatherConstruct.newInstance("slt");


        //3、获取声明的字段，需要传入字段的名称
        Field teacherAge = teacherClass.getDeclaredField("age");
        //teacherAge.setAccessible(true);  //私有方法需要设置
        //设置字段值，需要传入此对象以及参数值
        teacherAge.set(teacher, 50);


        //4、获取声明的函数，需要传入函数名
        Method showMethod = teacherClass.getDeclaredMethod("show", String.class);
        showMethod.setAccessible(true);
        //使用method的invoke代理方法，需要传入此对象以及所需参数值；
        //函数会返回一个Object对象，使用强制类型转成实际类型即可
        Object result = showMethod.invoke(teacher, "book ");

        System.out.println("result:" + result);

    }
}
