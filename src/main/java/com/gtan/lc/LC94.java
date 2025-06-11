package com.gtan.lc;

import com.gtan.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 94. 二叉树的中序遍历
 * @Version: 1.0
 */
public class LC94 {

    class Solution {
        List<Integer> res = new ArrayList<>();

        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) {
                return List.of();
            }
            if (root.left != null) {
                inorderTraversal(root.left);
            }
            res.add(root.val);
            if (root.right != null) {
                inorderTraversal(root.right);
            }
            return res;
        }
    }

}
