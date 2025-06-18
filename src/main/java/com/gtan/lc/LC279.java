package com.gtan.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-07
 * @Description: 279. 完全平方数
 * @Version: 1.0
 */
public class LC279 {

    class Solution {
        public int numSquares(int n) {
            // 首先找到小于n的完全平方数
            List<Integer> squares = new ArrayList<>();
            for (int i = 1; i <= Math.sqrt(n); i++) {
                squares.add(i * i);
            }
            // dp[sum]: 和为 sum 的完全平方数的最少数量
            int[] dp = new int[n + 1];
            // 初始化为 n + 1, 因为后面要比较最少数量
            Arrays.fill(dp, n + 1);
            // 初始化: 和为 0 的完全平方数的最少数量为 0
            dp[0] = 0;
            // 自底向上遍历每一个和(递推 + 记忆化搜索)
            for (int sum = 1; sum <= n; sum++) {
                // 从不同路径中选择数量最少的路径
                for (int square : squares) {
                    if (sum - square >= 0) {
                        dp[sum] = Math.min(dp[sum], dp[sum - square] + 1);
                    }
                }
            }
            // 返回和为 sum = n 的完全平方数的最少数量
            return dp[n];
        }
    }

}
