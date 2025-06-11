package com.gtan.lc;

import java.util.List;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-07
 * @Description: 139. 单词拆分
 * @Version: 1.0
 */
public class LC139 {

    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            int n = s.length();
            boolean[] dp = new boolean[n + 1];
            dp[0] = true;
            for (int i = 0; i < n; i++) {
                if (!dp[i]) {
                    continue;
                }
                for (String word : wordDict) {
                    int end = i + word.length();
                    if (end <= n && word.startsWith(s.substring(i, end))) {
                        dp[end] = true;
                    }
                }
            }
            return dp[n];
        }
    }

}
