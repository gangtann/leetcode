package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-22
 * @Description: 1143. 最长公共子序列
 * @Version: 1.0
 */
public class LC1143 {

    /*
     * 题目：给定两个字符串 text1 和 text2，求它们的最长公共子序列（LCS）长度。
     *
     * 【总体思路 | 动态规划  O(m × n) 时间  O(m × n) 空间】
     * 1. 状态定义
     *      dp[i][j] 表示 text1 前 i 个字符（下标 0..i-1）与
     *                  text2 前 j 个字符（下标 0..j-1）之间的
     *                  最长公共子序列长度。
     *
     * 2. 状态转移
     *      若 text1[i-1] == text2[j-1]：
     *          dp[i][j] = dp[i-1][j-1] + 1       // 把这一对相同字符加入 LCS
     *      否则：
     *          dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     *                    // 要么丢弃 text1 当前字符，要么丢弃 text2 当前字符
     *
     * 3. 初始化
     *      第 0 行或第 0 列代表空串，与任何串的 LCS 长度都为 0，
     *      所以 dp 数组已按默认 0 填充即可。
     *
     * 4. 答案
     *      dp[m][n] —— 即两串全部字符参与计算后的 LCS 长度。
     *
     * 备注：
     *      为节省空间可将 dp 压缩至一维（n+1），但此实现保持二维易读性。
     */

    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length();   // text1 的长度
            int n = text2.length();   // text2 的长度

            // dp 数组：多开一行一列，索引 0 表示空串
            int[][] dp = new int[m + 1][n + 1];

            // 遍历两字符串的每个字符
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (text1.charAt(i) == text2.charAt(j)) {
                        // 当前字符相等：在 dp[i][j] 基础上 +1
                        dp[i + 1][j + 1] = dp[i][j] + 1;
                    } else {
                        // 当前字符不相等：取“去掉 text1 当前字符”或“去掉 text2 当前字符”二者较大者
                        dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                    }
                }
            }

            // dp[m][n] 即为最终最长公共子序列长度
            return dp[m][n];
        }
    }

}
