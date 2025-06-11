package com.gtan.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 131. 分割回文串
 * @Version: 1.0
 */
public class LC131 {

    class Solution {
        private List<List<String>> ans = new ArrayList<>();
        private List<String> path = new ArrayList<>();
        private String str;

        public List<List<String>> partition(String s) {
            this.str = s;
            dfs(0);
            return ans;
        }

        private void dfs(int i) {
            int n = str.length();
            if (i == n) {
                ans.add(new ArrayList<>(path));
                return;
            }
            for (int j = i; j < n; j++) {
                if (isPalindrome(i, j)) {
                    path.add(str.substring(i, j + 1));
                    dfs(j + 1);
                    path.remove(path.size() - 1);
                }
            }
        }

        private boolean isPalindrome(int i, int j) {
            char[] chars = str.toCharArray();
            while (i < j) {
                if (chars[i] != chars[j]) {
                    return false;
                }
                i++;
                j--;
            }
            return true;
        }
    }

}
