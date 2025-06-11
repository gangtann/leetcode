package com.gtan.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 17. 电话号码的字母组合
 * @Version: 1.0
 */
public class LC17 {

    class Solution {
        private String[] str = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        private StringBuilder path = new StringBuilder();
        private List<String> ans = new ArrayList<>();
        private char[] digits;

        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.length() == 0) {
                return ans;
            }
            this.digits = digits.toCharArray();
            dfs(0);
            return ans;
        }

        private void dfs(int i) {
            if (i == digits.length) {
                ans.add(path.toString());
                return;
            }
            int n = digits[i] - '0';
            char[] c = str[n].toCharArray();
            for (int k = 0; k < c.length; k++) {
                path.append(c[k]);
                dfs(i + 1);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

}
