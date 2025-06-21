package com.gtan.lc;

import java.util.Arrays;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-21
 * @Description: 62. 不同路径
 * @Version: 1.0
 */
public class LC62 {

    /**
     * 动态规划 = 递归 + 重复子问题(可记忆化)
     */
    class Solution {
        public int uniquePaths(int m, int n) {
            // dp数组含义: 从起始位置到当前位置的路径数
            int[][] dp = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(dp[i], 1);
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
            return dp[m-1][n-1];
        }
    }

}
