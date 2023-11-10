package com.stone.leecode;

import java.util.HashMap;
import java.util.Map;

public class IsAnagram {

    public static void main(String[] args) {

        if (isAnaram("fool", "loff")) {
            System.out.println("有效的字母异位词");
        } else {
            System.out.println("无效。。。。");
        }


    }


    public static boolean isAnaram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        Map<Character, Integer> m1 = new HashMap();
//        Map<Character, Integer> m2  = new HashMap();


        for (char c : s1.toCharArray()) {
            if (m1.containsKey(c)) {
                m1.put(c, m1.get(c) + 1);
                continue;
            }

            m1.put(c, 1);
        }

        for (char c : s2.toCharArray()) {
            if (m1.containsKey(c)) {
                m1.put(c, m1.get(c) - 1);
            } else {
                return false;
            }
            if (m1.get(c) == 0) {
                m1.remove(c);
            }
        }

        return m1.size() == 0;

    }
}
