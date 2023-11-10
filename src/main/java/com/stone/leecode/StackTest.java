package com.stone.leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StackTest {

    public static void main(String[] args) {
//        String str = "((([])";
//        String str = "([)]";
        String str = "()[]";
//        stackSolution(str);
        stackRealQueue();
    }

    public static void stackSolution(String str){
        Stack<String> s = new Stack<>();

        Map<String, String> map = new HashMap<String, String>(){{
            put(")", "(");
            put("}", "{");
            put("]", "[");
        }};

        for (int i = 0; i<str.length(); i++) {
            String s1 = String.valueOf(str.charAt(i));
            if (!map.containsKey(s1)) {
                s.push(s1);
            } else if (s.peek().equals(map.get(s1))){
                s.pop();
            } else {
                break;
            }
        }

        if (s.empty()) {
            System.out.println("输入的内容匹配！");
        } else {
            System.out.println("Fail, stack中还有的元素:");
            while (!s.isEmpty()) {
                System.out.print(s.pop());
            }
        }
    }

    //利用stack实现Queue先进先出
    public static void stackRealQueue() {
        Stack input = new Stack();
        Stack output = new Stack();
        System.out.println("压入3个数：");
        enqueue(input, 1);
        enqueue(input, 2);
        enqueue(input, 3);
        System.out.println("取出2个数：");
        popqueue(input, output, 2); //取出两个数
        System.out.println("压入3个数：");
        enqueue(input, 4);
        enqueue(input, 5);
        enqueue(input, 6);
        System.out.println("取出4个数：");
        popqueue(input, output, 4);//取出4个
        System.out.println("压入3个数：");
        enqueue(input, 7);
        enqueue(input, 8);
        enqueue(input, 9);
        System.out.println("取出剩余的数：");
        depqueue(input, output);//全部取出
    }

    //压栈
    private static void enqueue(Stack input, int value) {
        input.push(value);
    }

    //将input中的数据全部pop压到output中
    private static void popqueue(Stack input, Stack output) {
        while (!input.isEmpty()) {
            output.push(input.pop());
        }
    }


    /**
     * @param input
     * @param output
     * @param deSize 出栈个数
     */
    private static void popqueue(Stack input, Stack output, int deSize) {
        for (int i = 0; i < deSize; i++) {
            //首先判断out是否为空
            //若不为空，则output栈先pop完
            //若为空，则将input栈中的数据再次压到output中，再进行pop
            if (output.isEmpty()) {
                popqueue(input, output);
            }
            System.out.println("出列: " + output.pop());
        }
    }

    private static void depqueue(Stack input, Stack output) {
        popqueue(input, output, input.size() + output.size());
    }

}
