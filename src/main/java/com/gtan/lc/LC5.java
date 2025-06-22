package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-22
 * @Description: 5. 最长回文子串
 * @Version: 1.0
 */
public class LC5 {

    class Solution {
        public String longestPalindrome(String s) {
            int n = s.length();
            char[] chars = s.toCharArray(); // 将字符串转换为字符数组，便于访问
            int ans_len = 0;       // 当前找到的最长回文子串的长度
            int ans_start = 0;     // 当前找到的最长回文子串的起始索引

            for (int i = 0; i < n; i++) {
                // 情况一：以单个字符为中心的回文串（奇数长度）
                int l = i, r = i;
                while (l >= 0 && r < n && chars[l] == chars[r]) {
                    if (r - l + 1 > ans_len) {
                        ans_len = r - l + 1;
                        ans_start = l;
                    }
                    l--;
                    r++;
                }

                // 情况二：以两个字符之间为中心的回文串（偶数长度）
                l = i;
                r = i + 1;
                while (l >= 0 && r < n && chars[l] == chars[r]) {
                    if (r - l + 1 > ans_len) {
                        ans_len = r - l + 1;
                        ans_start = l;
                    }
                    l--;
                    r++;
                }
            }

            // 截取最长回文子串
            return s.substring(ans_start, ans_start + ans_len);
        }
    }

}
