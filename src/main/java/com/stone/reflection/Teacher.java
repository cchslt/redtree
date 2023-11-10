package com.stone.reflection;

/**
 * @author chen
 * @create 2019-11-12 22:11
 **/
public class Teacher {

    private String name;

    public Integer age;

    public Teacher() {
    }

    private Teacher(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String show(String msg) {
        System.out.println("调用私有方法");
        return msg + " test";
    }
}
