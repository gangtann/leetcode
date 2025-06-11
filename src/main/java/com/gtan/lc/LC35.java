package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-06
 * @Description: 35. 搜索插入位置
 * @Version: 1.0
 */
public class LC35 {

    class Solution {
        public int searchInsert(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }
    }

}
