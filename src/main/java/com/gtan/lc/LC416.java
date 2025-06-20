package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-19
 * @Description: 416. 分割等和子集
 * @Version: 1.0
 */
public class LC416 {

    /**
     * 0-1背包问题
     */
    class Solution {
        public boolean canPartition(int[] nums) {
            // 计算数组总和
            int totalSum = 0;
            for (int i = 0; i < nums.length; i++) {
                totalSum += nums[i];
            }
            // 如果总和是奇数，则不可能平分
            if (totalSum % 2 == 1) {
                return false;
            }
            // 目标子集和——总和的一半
            int targetSum = totalSum / 2;
            // dp[sum]: 在已经遍历过的那些 nums 元素中，是否存在一个子集，它们的和恰好等于 sum
            boolean[] dp = new boolean[targetSum + 1];
            // 初始化时，dp[0] = true，因为"什么都不选"可以凑出和为 0
            dp[0] = true;
            // sumLimit 表示当前已考虑元素后，能尝试更新的最大和
            int sumLimit = 0;
            // 保证了每个数只被用一次的 0–1 背包转化
            for (int num : nums) {
                // 最多只能增长到 targetSum
                sumLimit = Math.min(sumLimit + num, targetSum);
                // 0–1 背包：倒序更新 dp 数组，确保每个 num 只用一次
                for (int sum = sumLimit; sum >= num; sum--) {
                    // 如果之前已经能凑出 sum−num，那么加上当前这个 num 就能凑出 sum
                    dp[sum] = dp[sum] || dp[sum - num];
                }
                // 提前退出: 如果 targetSum 已经可达, 就能分割成功
                if(dp[targetSum]) {
                    return true;
                }
            }
            return dp[targetSum];
        }
    }

}
