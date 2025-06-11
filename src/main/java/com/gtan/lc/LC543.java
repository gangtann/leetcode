package com.gtan.lc;

import com.gtan.common.TreeNode;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 543. 二叉树的直径
 * @Version: 1.0
 */
public class LC543 {

    class Solution {
        private int maxLen = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            maxDepth(root);
            return maxLen;
        }

        private int maxDepth(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int left = maxDepth(node.left);
            int right = maxDepth(node.right);
            maxLen = Math.max(maxLen, left + right);
            return Math.max(left, right) + 1;
        }
    }

}
