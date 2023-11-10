package com.stone.leecode;

/**
 * @author chen
 * @create 2021-03-13 15:19
 **/

public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 8};
        System.out.println(rihgtBound(nums, 2));
        System.out.println(rihgtBound(nums, 3));
    }


    static int binary(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                left = mid + 1;
            }else if (nums[mid] < target) {
                right = mid - 1;
            }

        }

        return -1;
    }


    static int leftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
               right = mid -1;
            }else if (nums[mid] > target) {
                right = mid -1;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }

        }


        if (nums[left] != target || left >= nums.length) {
            return -1;
        }
        return left;
    }


    static int rihgtBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                left = mid + 1;
            }else if (nums[mid] > target) {
                right = mid -1;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }

        }


        if (nums[right] != target || right >= nums.length) {
            return -1;
        }
        return right;
    }
}
