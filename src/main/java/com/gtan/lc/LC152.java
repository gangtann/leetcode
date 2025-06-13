package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-13
 * @Description: 152. 乘积最大子数组
 * @Version: 1.0
 */
public class LC152 {

    /**
     * 思路和 <300. 最长递增子序列> 类似, 但有一些不同的地方需要区分
     * 1. 题目要求的是连续子数组, 因此不需要和前面每一个元素比较, 只需要比较前一个元素就行
     * 2. 因此我们不需要使用dp数组, 用一个变量记录即可
     * 3. 因为涉及到正数与负数间的乘法, 结果随时有可能从最大值变为最小值, 也可能从最小值变为最大值, 因此需要记录最小值与最大值
     * dp_min: 以第 i 个元素为结尾的非空连续子数组, 记录下其乘积的最小值
     *      初始值: nums[0], 因为数组至少有一个元素
     *      递推公式: dp_min = max(dp_max * nums[i], dp_min * nums[i]), nums[i])
     *      nums[i]需要参与比较的原因是它可能是最小值也可能是最大值:
     *          例如 [-1, -2], 以 -2 结尾的元素最小值就是 -2
     *          例如 [-1, 2], 以 2 结尾的元素最大值就是 2
     *          因此当前元素可能是最小值也可能是最大值
     * dp_max: 以第 i 个元素为结尾的非空连续子数组, 记录下其乘积的最大值
     *      初始值: nums[0], 因为数组至少有一个元素
     *      递推公式: dp_min = min(dp_max * nums[i], dp_min * nums[i]), nums[i])
     * ans: 从每一个以 i 结尾的最大乘积中选择最大值就是最终的结果
     */
    class Solution {
        public int maxProduct(int[] nums) {
            // ans 直接初始化为nums[0], 因为数组至少有一个元素
            int ans = nums[0];
            int dp_min = nums[0], dp_max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                // 必须用 temp 记录 dp_max, 否则计算 dp_min 时 dp_max 已经更新
                int temp = dp_max;
                dp_max = Math.max(Math.max(dp_max * nums[i], dp_min * nums[i]), nums[i]);
                dp_min = Math.min(Math.min(temp * nums[i], dp_min * nums[i]), nums[i]);
                ans = Math.max(dp_max, ans);
            }
            return ans;
        }
    }

}
