package com.gtan.lc;

import com.gtan.common.TreeNode;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-02
 * @Description: 101. 对称二叉树
 * @Version: 1.0
 */
public class LC101 {

    class Solution {
        public boolean isSymmetric(TreeNode root) {
            return isSameTree(root.left, root.right);
        }

        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null || q == null) {
                return q == p;
            }
            return p.val == q.val && isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
        }
    }

}
