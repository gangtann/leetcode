package com.gtan.lc;

import java.util.*;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-06
 * @Description: 51. N 皇后
 * @Version: 1.0
 */
public class LC51 {

    class Solution {
        // 最终的答案
        private List<List<String>> ans = new ArrayList<>();
        // 某一条路径上的答案
        private List<String> path = new ArrayList<>();
        // 判断某一列不重复
        private Set<Integer> col = new HashSet<>();
        // 判断正对角线不重复, 横纵坐标相加(i + j), 位于同一条正对角线上的元素和相同
        private Set<Integer> add = new HashSet<>();
        // 判断斜对角线不重复, 横纵坐标相加(i - j), 位于同一条斜对角线上的元素差相同
        private Set<Integer> sub = new HashSet<>();
        // 整个棋盘, 默认是 '.'
        private char[][] board;
        private int n;

        public List<List<String>> solveNQueens(int n) {
            this.n = n;
            char[][] board = new char[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(board[i], '.');
            }
            this.board = board;
            dfs(0);
            return ans;
        }

        private void dfs(int i) {
            // 边界条件: 棋盘每一行访问完结束
            if (i == n) {
                ans.add(new ArrayList<>(path));
                return;
            }

            /*
            * 难点: 如何判断行列与对角线元素不重复
            * 1. 循环过程中每一行只能选一个元素
            * 2. 通过Set集合存储列下标判断同一列中是否已有元素存在
            * 3. 通过Set集合存储(行下标 + 列下标)判断正对角线中是否已有元素存在
            * 4. 通过Set集合存储(行下标 - 列下标)判断斜对角线中是否已有元素存在
            * */
            for (int j = 0; j < n; j++) {
                if (!col.contains(j) && !add.contains(i + j) && !sub.contains(i - j)) {
                    board[i][j] = 'Q';
                    col.add(j);
                    add.add(i + j);
                    sub.add(i - j);
                    path.add(String.valueOf(board[i]));
                    dfs(i + 1);
                    // 注意要恢复原状态
                    board[i][j] = '.';
                    col.remove(j);
                    add.remove(i + j);
                    sub.remove(i - j);
                    path.remove(path.size() - 1);
                }
            }
        }
    }

}
