package com.stone.leecode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chen
 * @create 2019-01-16 23:34
 **/

public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
        Arrays.sort(nums);

        Set<int[]> list = new HashSet<>();
        for (int i =0 ; i<nums.length-2; i++) {
            int c = nums[i];
            int start = i+1;
            int end = nums.length -1;
            while (start < end) {
                int sum = nums[start] + nums[end] + c;
                if (sum > 0) {
                    --end;
                } else if (sum < 0) {
                    ++start;
                } else if (sum == 0) {
                    int[] aa = new int[]{c, nums[start], nums[end]};
                    list.add(aa);
                    ++start;
                    --end;
                    //TODO 需要排重
                }
            }
        }


        for (int[] a : list) {
            for (int i : a) {
                System.out.println(i);
            }
        }



//        for (int i : nums) {
//            System.out.println(i);
//        }
    }
}
