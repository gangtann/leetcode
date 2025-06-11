package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-07
 * @Description: 153. 寻找旋转排序数组中的最小值
 * @Version: 1.0
 */
public class LC153 {

    class Solution {
        /**
         * 难点: 整个数组分为两部分, 左边部分一定大于右边部分,
         * 根据nums[mid]和数组最右边的元素比较 判断当前元素是在左边部分还是右边部分
         * 如果当前元素在左边部分, 那么最小值一定在右边部分，往右边二分
         * 如果当前元素在右边部分, 那么最小值一定在右边部分的左边，往左边二分
         * @param nums 数组
         * @return 最小值
         */
        public int findMin(int[] nums) {
            int left = 0, right = nums.length - 1;
            int val = nums[right];
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] > val) {
                    left = mid + 1;
                } else if (nums[mid] <= val) {
                    right = mid - 1;
                }
            }
            return nums[left];
        }
    }

}
