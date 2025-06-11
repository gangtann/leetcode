package com.gtan.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 22. 括号生成
 * @Version: 1.0
 */
public class LC22 {

    class Solution {
        private List<String> ans = new ArrayList<>();
        private char[] path;
        private int n;

        public List<String> generateParenthesis(int n) {
            this.n = n;
            this.path = new char[2 * n];
            dfs(0, 0);
            return ans;
        }

        private void dfs(int i, int left) {
            if (i == 2 * n) {
                ans.add(new String(path));
                return;
            }
            if (left < n) {
                path[i] = '(';
                dfs(i + 1, left + 1);
            }

            if (left > i - left) {
                path[i] = ')';
                dfs(i + 1, left);
            }
        }
    }

}
