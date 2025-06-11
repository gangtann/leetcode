package com.gtan.lc;

import com.gtan.common.TreeNode;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-02
 * @Description: 110. 平衡二叉树
 * @Version: 1.0
 */
public class LC110 {

    class Solution {
        public boolean isBalanced(TreeNode root) {
            return maxDepth(root) == -1;
        }

        private int maxDepth(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int leftMaxDepth = maxDepth(node.left);
            if (leftMaxDepth == -1) {
                return -1;
            }
            int rightMaxDepth = maxDepth(node.right);
            if (rightMaxDepth == -1 || Math.abs(leftMaxDepth - rightMaxDepth) > 1) {
                return -1;
            }
            return Math.max(leftMaxDepth, rightMaxDepth) + 1;
        }
    }

}
