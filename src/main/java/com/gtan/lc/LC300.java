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
     * 难点: 画出递归图, 找到递推关系
     * 以第 n 个元素结尾的最长子序列的长度会被 n 之前的所有元素影响
     * 例如:
     *      以第 0 个元素结尾的最长子序列会影响它
     *      以第 1 个元素结尾的最长子序列会影响它
     *      ...
     *      以第 n-1 个元素结尾的最长子序列会影响它
     * 所以在计算以第 n 个元素结尾的最长子序列的长度时, 要考虑 n-1 个元素对它的影响, 也就是 n-1 条路径(子节点)
     * 同时, 只有 nums[i] > nums[j] 才是合法的路径
     * 注意: 最长递增子序列不一定是以最后一个元素为结尾的, 任意一个子序列都有可能
     *      所以需要比较以每一个元素为结尾的最长递增子序列, 其中最大值才是整个数组中的最长递增子序列
     */
    class Solution {
        public int lengthOfLIS(int[] nums) {
            // dp[i]: 以第 n 个元素结尾的最长子序列的长度
            int n = nums.length;
            int[] dp = new int[n];
            // dp数组初始值为1, 最长递增子序列长度至少为1, 比如只有一个元素或者所有元素相等
            Arrays.fill(dp, 1);
            // ans初始化同理: 答案最小值为 1
            int ans = 1;
            for (int i = 0; i < n; i++) {
                // 遍历每一条路径, 找到以第 i 个元素结尾的最长递增子序列的长度
                for (int j = 0; j < i; j++) {
                    // 在每一条路径中, 只有 nums[i] > nums[j] 才是合法的路径, 才能计算以第 i 个结尾的最长递增子序列的长度
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
