package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-07
 * @Description: 34. 在排序数组中查找元素的第一个和最后一个位置
 * @Version: 1.0
 */
public class LC34 {

    class Solution {
        private int[] nums;
        private int target;

        public int[] searchRange(int[] nums, int target) {
            this.nums = nums;
            this.target = target;
            int left = binSearch(0);
            int right = binSearch(1);
            return new int[]{left, right};
        }

        /**
         * 难点: 分两次搜索, 一次搜索最左边的索引，一次搜索最右边的索引
         * @param isLeft 0: 最左边     1: 最右边
         * @return 索引
         */
        private int binSearch(int isLeft) {
            int left = 0, right = nums.length - 1;
            int idx = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    /*
                    * idx保存第一次搜索到的索引
                    * 如果搜索最左边的元素，继续往左边二分搜索, 找到了就更新idx
                    * 如果搜索最右边的元素，继续往右边二分搜索, 找到了就更新idx
                    * */
                    idx = mid;
                    if (isLeft == 0) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
            return idx;
        }
    }

}
