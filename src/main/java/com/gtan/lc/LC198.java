package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-07
 * @Description: 198. 打家劫舍
 * @Version: 1.0
 */
public class LC198 {

    class Solution {
        public int rob(int[] nums) {
            int pre2 = 0, pre1 = 0;
            for (int i = 0; i < nums.length; i++) {
                int cur = Math.max(pre1, pre2 + nums[i]);
                pre2 = pre1;
                pre1 = cur;
            }
            return pre1;
        }
    }

}
