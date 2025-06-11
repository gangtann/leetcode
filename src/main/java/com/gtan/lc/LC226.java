package com.gtan.lc;

import com.gtan.common.TreeNode;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 226. 翻转二叉树
 * @Version: 1.0
 */
public class LC226 {

    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            dfs(root, root.left, root.right);
            return root;
        }

        private void dfs(TreeNode parent, TreeNode left, TreeNode right) {
            if (parent == null || (left == null && right == null)) {
                return;
            }
            TreeNode temp = left;
            parent.left = right;
            parent.right = temp;
            if (parent.left != null) {
                dfs(parent.left, parent.left.left, parent.left.right);
            }
            if (parent.right != null) {
                dfs(parent.right, parent.right.left, parent.right.right);
            }
        }
    }

}
