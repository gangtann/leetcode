package com.gtan.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 3. 无重复字符的最长子串
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 *
 * @author gangtann@126.com
 * @version 1.1
 * @since 2025-07-15
 */
public class LC3 {

    static class LongestSubstringWithoutRepeatingCharacters {
        /*
         * 【总体思路】
         * 使用滑动窗口 + 哈希表记录字符出现次数:
         * 1. 定义左右指针 left 和 right，right 向右移动扩展窗口；
         * 2. 每次将 cs[right] 的计数加 1，若出现重复则收缩左边界 left 直到无重复；
         * 3. 更新最大长度 ans = max(ans, right - left + 1)。
         */
        public int lengthOfLongestSubstringMap(String s) {
            /* 将字符串转换为字符数组 */
            char[] cs = s.toCharArray();

            /* 记录窗口内每个字符的出现次数 */
            Map<Character, Integer> map = new HashMap<>();

            /* 窗口左边界 */
            int left = 0;

            /* 记录最大无重复子串长度 */
            int ans = 0;

            for (int right = 0; right < cs.length; right++) {
                /* 增加当前字符的计数 */
                map.put(cs[right], map.getOrDefault(cs[right], 0) + 1);

                /* 当窗口中出现重复字符时，收缩窗口 */
                while (map.get(cs[right]) > 1) {
                    /* 左边界字符计数减 1 */
                    map.put(cs[left], map.get(cs[left]) - 1);
                    /* 左边界右移 */
                    left++;
                }

                /* 更新最大长度 */
                ans = Math.max(ans, right - left + 1);
            }

            /* 返回结果 */
            return ans;
        }

        /**
         * 使用滑动窗口方法解决无重复字符的最长子串问题
         * 时间复杂度: O(n) - 每个字符最多被访问两次
         * 空间复杂度: O(min(m, n)) - m是字符集大小，n是字符串长度
         */
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            Set<Character> charSet = new HashSet<>();
            // 滑动窗口左边界
            int left = 0;
            int maxLength = 0;

            // 滑动窗口右边界从0开始遍历
            for (int right = 0; right < s.length(); right++) {
                // 如果字符已存在，移动左边界直到无重复
                while (charSet.contains(s.charAt(right))) {
                    charSet.remove(s.charAt(left));
                    left++;
                }

                // 添加当前字符到集合
                charSet.add(s.charAt(right));

                // 更新最大长度
                maxLength = Math.max(maxLength, right - left + 1);
            }

            return maxLength;
        }

        /**
         * 另一种实现：使用数组代替HashSet，对于ASCII字符更高效
         */
        public int lengthOfLongestSubstringArray(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            // 使用数组记录字符最后出现的位置
            int[] lastSeen = new int[256];
            for (int i = 0; i < 256; i++) {
                lastSeen[i] = -1;
            }

            int left = 0;
            int maxLength = 0;

            for (int right = 0; right < s.length(); right++) {
                char c = s.charAt(right);

                // 如果字符之前出现过且在当前窗口内，移动左边界
                if (lastSeen[c] >= left) {
                    left = lastSeen[c] + 1;
                }

                // 更新字符最后出现位置
                lastSeen[c] = right;

                // 更新最大长度
                maxLength = Math.max(maxLength, right - left + 1);
            }

            return maxLength;
        }

    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();

        // 测试用例
        String[] testCases = {
                "abcabcbb",    // 预期输出: 3 ("abc")
                "bbbbb",       // 预期输出: 1 ("b")
                "pwwkew",      // 预期输出: 3 ("wke")
                "",            // 预期输出: 0
                " ",           // 预期输出: 1 (" ")
                "dvdf",        // 预期输出: 3 ("vdf")
                "anviaj",      // 预期输出: 5 ("nviaj")
                "aab"          // 预期输出: 2 ("ab")
        };

        System.out.println("测试Set方法：");
        for (String testCase : testCases) {
            int result = solution.lengthOfLongestSubstring(testCase);
            System.out.printf("输入: \"%s\" -> 输出: %d%n", testCase, result);
        }

        System.out.println("\n测试数组方法：");
        for (String testCase : testCases) {
            int result = solution.lengthOfLongestSubstringArray(testCase);
            System.out.printf("输入: \"%s\" -> 输出: %d%n", testCase, result);
        }
    }


}
