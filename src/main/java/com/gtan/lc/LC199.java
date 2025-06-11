package com.gtan.lc;

import com.gtan.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-02
 * @Description: 199. 二叉树的右视图
 * @Version: 1.0
 */
public class LC199 {

    class Solution {
        List<Integer> ans = new ArrayList<>();

        public List<Integer> rightSideView(TreeNode root) {
            dfs(root, 0);
            return ans;
        }

        private void dfs(TreeNode node, int depth) {
            if (node == null) {
                return;
            }
            if (ans.size() == depth) {
                ans.add(node.val);
            }
            dfs(node.right, depth + 1);
            dfs(node.left, depth + 1);
        }
    }

}
