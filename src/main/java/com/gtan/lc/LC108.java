package com.gtan.lc;

import com.gtan.common.TreeNode;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 108. 将有序数组转换为二叉搜索树
 * @Version: 1.0
 */
public class LC108 {

    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            return binary(nums, left, right);
        }

        private TreeNode binary(int[] nums, int start, int end) {
            if (start > end) {
                return null;
            }
            int mid = start + (end - start) / 2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = binary(nums, start, mid - 1);
            node.right = binary(nums, mid + 1, end);
            return node;
        }
    }

}
