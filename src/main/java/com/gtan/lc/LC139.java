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
            /*
             * dp[i]: 字符串 s 中 [0, i) 这一部分的子串能与字典 wordDict 中的一个或多个单词拼接出, 为 true
             * 也就是说, 字符串 s 中索引 i 之前的子串可以通过字典 wordDict 中的单词拼接出
             * */
            boolean[] dp = new boolean[n + 1];
            // 默认为true, 只有dp[i]为true, 才会继续匹配
            dp[0] = true;
            for (int i = 0; i < n; i++) {
                if (!dp[i]) {
                    continue;
                }
                // 遍历字典中的所有单词, 如果匹配设置dp[end] = true, 表示字符串 s 中索引 end 之前的子串可以通过字典 wordDict 中的单词拼接出
                for (String word : wordDict) {
                    int end = i + word.length();
                    if (end <= n && word.startsWith(s.substring(i, end))) {
                        dp[end] = true;
                    }
                }
            }
            // dp[n]表示字符串 s 中索引 n = s.length() 之前的子串是否可以通过字典 wordDict 中的单词拼接出
            return dp[n];
        }
    }

}
