package com.gtan.lc;

import com.gtan.common.TreeNode;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 437. 路径总和 III
 * @Version: 1.0
 */
public class LC437 {

    class Solution {
        private int ans = 0;

        public int pathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return 0;
            }
            dfs(root, targetSum);
            pathSum(root.left, targetSum);
            pathSum(root.right, targetSum);
            return ans;
        }

        private void dfs(TreeNode node, long targetSum) {
            if (node == null) {
                return;
            }
            if (node.val == targetSum) {
                ans++;
            }
            dfs(node.left, targetSum - node.val);
            dfs(node.right, targetSum - node.val);
        }
    }

}
