package com.gtan.lc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-21
 * @Description: 32. 最长有效括号
 * @Version: 1.0
 */
public class LC32 {

    /**
     * 使用栈解决
     */
    class Solution {
        public int longestValidParentheses(String s) {
            Deque<Integer> stack = new ArrayDeque<>();
            int n = s.length();
            char[] chars = s.toCharArray();
            // marks数组含义: 当前括号是否是有效括号
            boolean[] marks = new boolean[n];

            for (int i = 0; i < n; i++) {
                if (chars[i] == '(') {
                    stack.push(i);
                } else {
                    if (!stack.isEmpty()) {
                        Integer idx = stack.pop();
                        marks[idx] = true;
                        marks[i] = true;
                    }
                }
            }

            int count = 0;
            int ans = 0;
            for (boolean b : marks) {
                if (b) {
                    count++;
                    ans = Math.max(ans, count);
                } else {
                    count = 0;
                }
            }

            return ans;
        }
    }

    class Solution2 {
        public int longestValidParentheses(String s) {
            Stack<Integer> stack = new Stack<>();
            int n = s.length();
            char[] chars = s.toCharArray();
            boolean[] marks = new boolean[n];

            for (int i = 0; i < n; i++) {
                if (chars[i] == '(') {
                    stack.push(i);
                } else {
                    if (!stack.isEmpty()) {
                        Integer idx = stack.pop();
                        marks[idx] = true;
                        marks[i] = true;
                    }
                }
            }

            int count = 0;
            int ans = 0;
            for (boolean b : marks) {
                if (b) {
                    count++;
                    ans = Math.max(ans, count);
                } else {
                    count = 0;
                }
            }

            return ans;
        }
    }

}
