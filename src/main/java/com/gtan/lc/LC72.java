package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-23
 * @Description: 72. 编辑距离
 * @Version: 1.0
 */
public class LC72 {

    class Solution {

        /*
         * 【第一步：构造问题】
         *   给定两个字符串 word1 和 word2，每次可以进行插入、删除或替换操作，
         *   求将 word1 转换为 word2 的最少操作数，即最小编辑距离。
         *
         * 【第二步：拆解子问题】
         *   使用动态规划，定义状态 dp[i][j] 表示将 word1 的前 i 个字符变成 word2 的前 j 个字符的最小编辑距离。
         *
         * 【第三步：求解简单子问题】
         *   当其中一个字符串为空时，编辑距离就是另一个字符串的长度：
         *   - 将非空变为空：需要全部删除
         *   - 将空变为非空：需要全部插入
         *
         * 【第四步：通过已知问题来求解】
         *   - 若 word1.charAt(i - 1) == word2.charAt(j - 1)，无需操作：dp[i][j] = dp[i - 1][j - 1]
         *   - 否则：
         *     插入：dp[i][j - 1] + 1
         *     删除：dp[i - 1][j] + 1
         *     替换：dp[i - 1][j - 1] + 1
         *   - 取三者中的最小值作为当前状态结果
         *
         * 【第五步：判断复杂度】
         *   时间复杂度：O(m * n)，双重循环遍历两个字符串
         *   空间复杂度：O(m * n)，使用二维数组存储中间结果
         *
         * 【补充：三种编辑操作的实际含义及示例】
         *
         *   - 插入（dp[i][j - 1] + 1）：
         *       含义：将 word1[0..i-1] 转换为 word2[0..j-2] 后，在末尾插入 word2[j-1]。
         *       示例：word1 = "ho"，word2 = "hos"，dp[2][3] = dp[2][2] + 1，相当于在 "ho" 后插入 's' 得到 "hos"
         *
         *   - 删除（dp[i - 1][j] + 1）：
         *       含义：将 word1[0..i-2] 转换为 word2[0..j-1] 后，删除 word1[i-1]。
         *       示例：word1 = "horse"，word2 = "ros"，dp[5][3] = dp[4][3] + 1，表示从 "hors" → "ros" 后，删掉 'e'
         *
         *   - 替换（dp[i - 1][j - 1] + 1）：
         *       含义：将 word1[0..i-2] 转换为 word2[0..j-2] 后，将 word1[i-1] 替换为 word2[j-1]。
         *       示例：word1 = "hi"，word2 = "ho"，dp[2][2] = dp[1][1] + 1，将 'i' 替换为 'o' 得到 "hot"
         */

        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();

            // 定义 dp 数组，dp[i][j] 表示 word1 前 i 个字符与 word2 前 j 个字符的最小编辑距离
            int[][] dp = new int[m + 1][n + 1];

            // 初始化第一列：word1 到空串，需要删除操作
            for (int i = 0; i <= m; i++) {
                dp[i][0] = i;
            }

            // 初始化第一行：空串到 word2，需要插入操作
            for (int j = 0; j <= n; j++) {
                dp[0][j] = j;
            }

            // 状态转移
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        // 如果最后一个字符相同，无需新增操作
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        // 三种情况取最小：
                        // 1. 插入 dp[i][j - 1] + 1
                        // 2. 删除 dp[i - 1][j] + 1
                        // 3. 替换 dp[i - 1][j - 1] + 1
                        dp[i][j] = Math.min(
                                Math.min(dp[i - 1][j], dp[i][j - 1]),
                                dp[i - 1][j - 1]
                        ) + 1;
                    }
                }
            }

            // 返回最终结果
            return dp[m][n];
        }
    }


}
