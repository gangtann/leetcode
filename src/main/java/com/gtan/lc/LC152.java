package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-13
 * @Description: 152. 乘积最大子数组
 * @Version: 1.0
 */
public class LC152 {

    /**
     * 本题只有前一个元素会影响当前状态
     */
    class Solution {
        public int maxProduct(int[] nums) {
            /*
             * dp数组的定义是: 以第 i 个元素为结尾的乘积最大子数组的最大乘积
             * 只不过第 i 个元素只会被前一个元素影响(以第 i-1 个元素为结尾的子数组), 因此只需要用一个变量保存
             * 又因为本题的特殊性, 第 i 个元素可能是正数也可能是负数,
             * 如果是负数, 以第 i-1 个元素为结尾的乘积最小子数组的最小乘积, 与第 i 个元素相乘, 也可能是最大值
             * 因此还需要记录以第 i 个元素为结尾的乘积最小子数组的最小乘积
             * 初始化: 至少有一个元素, 初始化为nums[0]
             * */
            int dpMax = nums[0];
            int dpMin = nums[0];
            // dp数组中的最大值就是乘积最大子数组的最大乘积
            int ans = nums[0];
            for (int i = 1; i < nums.length; i++) {
                int tempMax = nums[i] * dpMax;
                int tempMin = nums[i] * dpMin;
                // nums[i]可能是最小值也可能是最大值
                dpMax = Math.max(Math.max(tempMax, tempMin), nums[i]);
                dpMin = Math.min(Math.min(tempMax, tempMin), nums[i]);
                ans = Math.max(ans, dpMax);
            }
            return ans;
        }
    }

}
