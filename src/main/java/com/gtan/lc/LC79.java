package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-05
 * @Description: 79. 单词搜索
 * @Version: 1.0
 */
public class LC79 {

    class Solution {
        private char[][] board;
        private char[] word;
        // 需要一个path保存已经访问过的元素，避免回头造成重复访问从而出错: 0 -> 未访问, 1 -> 已访问
        private int[][] path;

        public boolean exist(char[][] board, String word) {
            this.board = board;
            this.word = word.toCharArray();
            this.path = new int[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (dfs(i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(int i, int j, int k) {
            // 最多访问k次, 因为word中最多k个字符
            if (k == word.length) {
                return true;
            }

            int row = board.length;
            int col = board[0].length;
            /*
            * 难点: 边界条件的处理
            * 1. 向左向右向上向下访问不能越界, 对应 i >= row || i < 0 || j >= col || j < 0
            * 2. 第k次访问, 当前的访问的元素board[i][j]和第k个元素word[k]如果不相等，后续不用再访问了
            * 3. 如果当前元素已经被访问过了，后续也不需要访问了
            * 直接返回false
            * */
            if (i >= row || i < 0 || j >= col || j < 0 || board[i][j] != word[k] || path[i][j] == 1) {
                return false;
            }
            // 难点: 需要设置当前元素已经访问
            path[i][j] = 1;
            boolean ans = false;
            ans |= dfs(i + 1, j, k + 1);
            ans |= dfs(i, j + 1, k + 1);
            ans |= dfs(i, j - 1, k + 1);
            ans |= dfs(i - 1, j, k + 1);
            // 难点: 需要恢复原状态
            path[i][j] = 0;
            return ans;
        }
    }

}
