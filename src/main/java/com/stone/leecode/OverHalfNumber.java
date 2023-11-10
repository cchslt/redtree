package com.stone.leecode;

/**
 * @author chen
 * @create 2022-03-21 14:50
 * @Desc 一个数组中超过，有一个数字超过一半的数字，求出这个数
 **/

public class OverHalfNumber {

    public static void main(String[] args) {

        int[] nums = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(solution(nums));

    }

    public static int solution(int[] nums) {
        //目前所剩下的数字有多少个
        int count = 0;
        //当前留下的数据的是那个
        int currentIndex = 0;


        for (int num : nums) {
            if (count == 0) {
                currentIndex = num;
            }

            if (num == currentIndex) {
                count++;
            } else {
                count--;
            }
        }

        return currentIndex;
    }
}
