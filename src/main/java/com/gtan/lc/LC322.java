package com.gtan.lc;

import java.util.Arrays;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-07
 * @Description: 322. 零钱兑换
 * @Version: 1.0
 */
public class LC322 {

    class Solution {
        public int coinChange(int[] coins, int amount) {
            // dp[sum]: 可以凑成总金额为 sum 所需的最少的硬币个数
            int[] dp = new int[amount + 1];
            // 因为之后要比较最少的硬币个数, 初始化为一个不可能的最大值
            Arrays.fill(dp, amount + 1);
            // 可以凑成总金额为 0 所需的最少的硬币个数为 0
            dp[0] = 0;
            // 动态规划: 自底向上(递推 + 记忆化搜索)
            for (int sum = 1; sum <= amount; sum++) {
                // 从不同的路径中选择硬币最少的路径
                for (int coin : coins) {
                    if (coin <= sum) {
                        dp[sum] = Math.min(dp[sum], dp[sum - coin] + 1);
                    }
                }
            }
            // 返回可以凑成总金额为 sum = n 所需的最少的硬币个数; 如果不存在则返回 -1
            if (dp[amount] == amount + 1) {
                return -1;
            } else {
                return dp[amount];
            }
        }
    }

}
