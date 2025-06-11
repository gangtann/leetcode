package com.gtan.lc;

import com.gtan.common.TreeNode;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 124. 二叉树中的最大路径和
 * @Version: 1.0
 */
public class LC124 {

    class Solution {
        private int ans = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            dfs(root);
            return ans;
        }

        private int dfs(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int left = Math.max(0, dfs(node.left));
            int right = Math.max(0, dfs(node.right));
            ans = Math.max(ans, node.val + left + right);
            return node.val + Math.max(left, right);
        }
    }

}
