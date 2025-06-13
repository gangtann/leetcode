package com.gtan.lc;

import java.util.Arrays;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-13
 * @Description: 300. 最长递增子序列
 * @Version: 1.0
 */
public class LC300 {

    /**
     * 找到 <递归 + 记忆化搜索> 并转换成递推
     * dp[i]: 以第n个元素结尾的最长子序列的长度
     * 依次比较nums[i]和nums[0...i-1]的大小
     * 如果nums[i] > nums[i], 则选择dp[i]和dp[j] + 1中的最大值 -> 递推公式: max{dp[i], dp[j] + 1}
     * 同时维护一个ans变量, 记录dp数组中的最大值, 遍历结束后就是最长递增子序列的长度
     */
    class Solution {
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1);
            int ans = 1;
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                        ans = Math.max(ans, dp[i]);
                    }
                }
            }
            return ans;
        }
    }

}
