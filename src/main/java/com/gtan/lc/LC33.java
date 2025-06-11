package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-07
 * @Description: 33. 搜索旋转排序数组
 * @Version: 1.0
 */
public class LC33 {

    class Solution {
        /**
         * 难点: 左边部分一定是大于右边部分的, 根据不同的条件判断二分的方向
         * target 有可能在左边部分, 也有可能在右边部分
         * nums[mid] 有可能在左边部分, 也有可能在右边部分
         * 不同情况选择不同
         * @param nums
         * @param target
         * @return
         */
        public int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            int val = nums[right];
            if (val == target) {
                return right;
            }
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    /*
                    * 当 nums[mid] < target, 一共有三种情况
                    * 都在左边或都在右边, 此时都是往右边二分, left = mid + 1;
                    * nums[mid]在右边, target在左边, 此时都是往左边二分, right = mid - 1;
                    * */
                    if (target > val && nums[mid] <= val) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    /*
                     * 当 nums[mid] > target, 一共有三种情况
                     * 都在左边或都在右边, 此时都是往左边二分, right = mid - 1;
                     * nums[mid]在左边, target在右边, 此时都是往右边二分, left = mid + 1;
                     * */
                    if (target <= val && nums[mid] > val) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
            return -1;
        }
    }

}
