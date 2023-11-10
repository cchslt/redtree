package com.stone.leecode;

import java.util.Arrays;

/**
 * @author chen
 * @create 2021-03-11 00:09
 **/

public class Money {

    /**
     * 凑硬币问题
     * @param args
     */
    public static void main(String[] args) {

        int[] coins ={3, 5, 5};
        int amount = 7;

        System.out.println(coinChange(coins, amount));
    }

    private static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if ((i - coin) < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
