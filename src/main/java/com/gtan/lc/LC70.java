package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-07
 * @Description: 70. 爬楼梯
 * @Version: 1.0
 */
public class LC70 {

    /**
     * pre2 -> pre1 -> cur
     */
    class Solution {
        public int climbStairs(int n) {
            int pre1 = 1, pre2 = 1;
            for (int i = 2; i <= n; i++) {
                int cur = pre1 + pre2;
                pre2 = pre1;
                pre1 = cur;
            }
            return pre1;
        }
    }

}
