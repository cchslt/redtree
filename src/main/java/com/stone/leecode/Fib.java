package com.stone.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen
 * @create 2021-03-09 07:43
 **/

public class Fib {
    /**
     * 斐波那契数列多种解法
     * @param args
     */

    public static void main(String[] args) {
//        System.out.println(fib(3));
//        System.out.println(fib(4));
//        System.out.println(fib(5));
//        System.out.println(fib(6));
        System.out.println(fib1(100L));
        System.out.println(fib2(100));
        System.out.println(fib3(100));
    }



    private static Long fib1(Long n) {
        if (n < 0) {
            return 0L;
        }
        Map<Long, Long> map = new HashMap();


        return helper(map, n);

    }


    private static Long fib2(int n) {
        Long[] db = new Long[n + 1];
        db[1] = db[2] = 1L;

        for (int i = 3; i <= n; i++) {
            db[i] = db[i - 1] + db[i - 2];
        }

        return  db[n];
    }

    private static Long fib3(int n) {

        if (n == 2 || n== 1) {
            return 1L;
        }

        Long prev = 1L; Long cur = 1L;

        for (int i = 3; i<= n; i++) {
            Long sum = prev + cur;
            prev = cur;
            cur = sum;
        }

        return cur;

    }


    private static Long helper(Map<Long, Long> map, Long n) {
        if (n == 1 || n == 2) return 1L;

        if (map.get(n) != null) return map.get(n);

        map.put(n, helper(map, n - 1) + helper(map, n -2));
        return map.get(n);

    }
}
