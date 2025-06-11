package com.gtan.lc;

import com.gtan.common.TreeNode;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-02
 * @Description: 100. 相同的树
 * @Version: 1.0
 */
public class LC100 {

    class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null || q == null) {
                return q == p;
            }
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

}
