package com.gtan.lc;

import com.gtan.common.TreeNode;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 98. 验证二叉搜索树
 * @Version: 1.0
 */
public class LC98 {

    class Solution {
        public boolean isValidBST(TreeNode root) {
            return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean isBST(TreeNode node, long min, long max) {
            if (node == null) {
                return true;
            }
            if (node.val > min && node.val < max) {
                return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
            }
            return false;
        }
    }

}
