package com.gtan.lc;

import com.gtan.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 230. 二叉搜索树中第 K 小的元素
 * @Version: 1.0
 */
public class LC230 {

    /**
     * 递归
     */
    class Solution {
        private List<Integer> ans = new ArrayList<>();

        public int kthSmallest(TreeNode root, int k) {
            dfs(root);
            return ans.get(k - 1);
        }

        /**
         * 中序遍历
         */
        private void dfs(TreeNode node) {
            if (node == null) {
                return;
            }
            dfs(node.left);
            ans.add(node.val);
            dfs(node.right);
        }
    }

    /**
     * 迭代
     */
    class Solution1 {

//        public int kthSmallest(TreeNode root, int k) {
//
//        }

    }

}
