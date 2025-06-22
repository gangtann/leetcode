package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-22
 * @Description: 64. 最小路径和
 * @Version: 1.0
 */
public class LC64 {

    /**
     * 解题思想与 LC62. 不同路径 一样
     */
    class Solution {
        public int minPathSum(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            // dp数组定义: 表示从起点 (0, 0) 出发，到达网格中的位置 (i, j) 的所有路径中，路径数字总和的最小值。
            int[][] dp = grid;
            // 初始化第一行：只能从左往右走，因此累加前一列的最小路径和
            for (int col = 1; col < n; col++) {
                dp[0][col] = dp[0][col - 1] + grid[0][col];
            }
            // 初始化第一列：只能从上往下走，因此累加前一行的最小路径和
            for (int row = 1; row < m; row++) {
                dp[row][0] = dp[row - 1][0] + grid[row][0];
            }
            /*
            * 填充其余位置的 dp 值
            * 每个位置只能从左边或上边走过来，选择路径和较小的那个方向
            * */
            for (int row = 1; row < m; row++) {
                for (int col = 1; col < n; col++) {
                    dp[row][col] = Math.min(dp[row - 1][col], dp[row][col - 1]) + grid[row][col];
                }
            }
            // 返回到达右下角的最小路径和
            return dp[m - 1][n - 1];
        }
    }

}
