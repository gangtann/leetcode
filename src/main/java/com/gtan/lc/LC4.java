package com.gtan.lc;

import java.util.Arrays;
import java.util.Map;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-07
 * @Description: 4. 寻找两个正序数组的中位数
 * @Version: 1.0
 */
public class LC4 {

    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                int[] temp = nums1;
                nums1 = nums2;
                nums2 = temp;
            }
            int m = nums1.length, n = nums2.length;
            int left = 0, right = m;
            while (left <= right) {
                int i = left + (right - left) / 2;
                int j = (m + n + 1) / 2 - i;
                int left1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
                int right1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
                int left2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
                int right2 = (j == n) ? Integer.MAX_VALUE : nums2[j];
                if (left1 <= right2 && left2 <= right1) {
                    if ((m + n) % 2 == 1) {
                        return Math.max(left1, left2);
                    } else {
                        return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                    }
                } else if (left1 > right2) {
                    right = i - 1;
                } else if (left2 > right1) {
                    left = i + 1;
                }
            }
            return 0.0;
        }
    }

}
