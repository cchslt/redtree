package com.stone.leecode;

/**
 * @author chen
 * @create 2022-01-19 15:17
 **/

public class Median {

    public static void main(String[] args) {
        int[] array = new int[]{5,6,7,8,9,1,2,3,4};
        int[] array2 = new int[]{1,2,3,4,5, 6,7,8,9};
        int[] array3 = new int[]{9,1,2,3,4,5, 6,7,8};
        int[] array4 = new int[]{8, 9, 1,2,3,4,5, 6,7};
        System.out.println(solution(array));
        System.out.println(solution(array2));
        System.out.println(solution(array3));
        System.out.println(solution(array4));

    }

    public static int solution(int[] array) {
        int temp = array[0];
        int length = array.length;
        int n = 0;

        if (temp < array[length - 1]) {
            return array[length / 2];
        }

        for (int i = 1; i < length; i++) {
            if (temp < array[i]) {
                temp = array[i];
            } else {
                n = i;
                break;
            }
        }

        int[] target = new int[length];
        for (int i = 0; i < length; i++) {
            if ((n + i) < length){
                target[i] = array[n + i];
            } else {
                target[i] = array[(n + i) - length];
            }
        }


        return target[length / 2];
    }
}
