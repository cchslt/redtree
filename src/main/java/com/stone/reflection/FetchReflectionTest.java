package com.stone.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author chen
 * @create 2019-11-12 22:15
 **/

public class FetchReflectionTest {

    public static void main(String[] args) throws ClassNotFoundException {
        //第一种方式获取Class对象，通过类的全路径字符串获取 Class 对象
        Class teacher1 = Class.forName("com.stone.reflection.Teacher");
        //第二种方式，需要导入包
        Class teacher2 = Teacher.class;

        //第三种不需要在反射
        Teacher teacher = new Teacher();
        Class teacher3 = teacher.getClass();


        System.out.println("第一种方式: " + teacher1);
        System.out.println("第二种方式: " + teacher2);
        System.out.println("第三种方式: " + teacher3);


        //获取所有的属性
        Field[] declaredFieldList = teacher1.getDeclaredFields();
        for (Field field : declaredFieldList) {
            System.out.println("declared field: " + field);
        }

        //只获取public属性
        Field[] fieldList = teacher1.getFields();
        for (Field field : fieldList) {
            System.out.println("field: " + field);
        }


        //获取所有的构造方法
        Constructor[] declaredConstructorList = teacher1.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructorList) {
            System.out.println("declared Constructor: " + constructor);
        }

        //只获取public构造方式
        Constructor[] constructorList = teacher1.getDeclaredConstructors();
        for (Constructor constructor : constructorList) {
            System.out.println("Constructor: " + constructor);
        }



        //获取所有的非构造方法
        Method[] declaredMethodList = teacher1.getDeclaredMethods();
        for (Method method : declaredMethodList) {
            System.out.println("declared method: " + method);
        }
        //获取public非构造方法
        Method[] methodList = teacher1.getMethods();
        for (Method method : methodList) {
            System.out.println(" method: " + method);
        }

    }
}
