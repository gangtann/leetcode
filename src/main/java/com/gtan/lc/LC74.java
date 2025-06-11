package com.gtan.lc;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-07
 * @Description: 74. 搜索二维矩阵
 * @Version: 1.0
 */
public class LC74 {

    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            /*
             * 根据二维矩阵最后一列的元素判断位于哪一行, 在列上二分查找
             * */
            int m = matrix.length - 1, n = matrix[0].length - 1;
            int left = 0, right = m;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (matrix[mid][n] == target) {
                    return true;
                } else if (matrix[mid][n] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            // left有可能越界, 说明元素不在任何一行中
            if (left > m) {
                return false;
            }
            /*
            * 锁定某一行, 二分查找
            * */
            int[] nums = matrix[left];
            left = 0;
            right = n;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return true;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return false;
        }
    }

}
