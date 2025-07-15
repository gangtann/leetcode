package com.gtan.lc;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 *
 * @author gangtann@126.com
 * @version 1.0
 * @since 2025-07-15
 */
public class LC3 {

    class Solution {
        /*
         * 【总体思路】
         * 使用滑动窗口 + 哈希表记录字符出现次数:
         * 1. 定义左右指针 left 和 right，right 向右移动扩展窗口；
         * 2. 每次将 cs[right] 的计数加 1，若出现重复则收缩左边界 left 直到无重复；
         * 3. 更新最大长度 ans = max(ans, right - left + 1)。
         */
        public int lengthOfLongestSubstring(String s) {
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
    }


}
