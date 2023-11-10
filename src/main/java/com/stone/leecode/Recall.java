package com.stone.leecode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chen
 * @create 2021-03-11 22:53
 **/

public class Recall {

    /**
     * 所有列举数据的全排列
     * 回溯算法需要遍历N！次，穷举所有的元素，时间复杂度为O(N!)
     * @param args
     */

    private static List<List<Integer>> data = new LinkedList<>();


    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4};

        //记录路径
        LinkedList<Integer> track = new LinkedList<>();

        recall(nums, track);

        for (List<Integer> list : data) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }


    private static void recall(int[] nums, LinkedList<Integer> track) {
        //触发结束条件
        if (track.size() == nums.length) {
            data.add(new LinkedList<>(track));
            return;
        }

        for (int num : nums) {
            //排除已遍历的元素
            if (track.contains(num)) {
                continue;
            }

            //做选择
            track.add(num);
            //递归遍历，进入下一层决策,再做选择
            recall(nums, track);
            //取消选择
            track.removeLast();
        }

    }
}
