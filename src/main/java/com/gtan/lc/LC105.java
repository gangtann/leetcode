package com.gtan.lc;

import com.gtan.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 105. 从前序与中序遍历序列构造二叉树
 * @Version: 1.0
 */
public class LC105 {

    class Solution {
        Map<Integer, Integer> map = new HashMap<>();
        int[] preorder;
        int[] inorder;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            int preEnd = preorder.length - 1;
            int inEnd = inorder.length - 1;
            for (int i = 0; i <= inEnd; i++) {
                map.put(inorder[i], i);
            }
            this.preorder = preorder;
            this.inorder = inorder;
            return dfs(0, preEnd, 0, inEnd);
        }

        private TreeNode dfs(int preStart, int preEnd, int inStart, int inEnd) {
            if (preStart > preEnd || inStart > inEnd) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[preStart]);
            int inIdx = map.get(preorder[preStart]);
            int leftSize = inIdx - inStart;
            root.left = dfs(preStart + 1, preStart + leftSize, inStart, inIdx - 1);
            root.right = dfs(preStart + leftSize + 1, preEnd, inIdx + 1, inEnd);
            return root;
        }
    }

}
