package com.gtan.lc;

import com.gtan.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 114. 二叉树展开为链表
 * @Version: 1.0
 */
public class LC114 {

    class Solution {
        private List<TreeNode> ans = new ArrayList<>();

        public void flatten(TreeNode root) {
            dfs(root);
            int n = ans.size();
            for (int i = 0; i < n; i++) {
                TreeNode cur = ans.get(i);
                cur.left = null;
                if (i < n - 1) {
                    cur.right = ans.get(i + 1);
                } else {
                    cur.right = null;
                }
            }
        }

        private void dfs(TreeNode node) {
            if (node == null) {
                return;
            }
            ans.add(node);
            dfs(node.left);
            dfs(node.right);
        }
    }

}
