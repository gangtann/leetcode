package com.gtan.lc;

import com.gtan.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-02
 * @Description: 104. 二叉树的最大深度
 * @Version: 1.0
 */
public class LC104 {

    /**
     * 递归
     */
    class Solution1 {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

    /**
     * DFS
     */
    class Solution2 {
        // 内部类：用来在栈中同时保存节点和该节点对应的深度
        class NodeDepth {
            TreeNode node;
            int depth;

            public NodeDepth(TreeNode node, int depth) {
                this.node = node;
                this.depth = depth;
            }
        }

        public int maxDepth(TreeNode root) {
            // 如果传入的根节点为空，直接返回 0
            if (root == null) {
                return 0;
            }
            int maxDepth = 0;
            // 用 Stack 保存“节点 + 深度”对
            Stack<NodeDepth> stack = new Stack<>();
            // 从根节点开始，初始深度为 1
            stack.push(new NodeDepth(root, 1));
            while (!stack.isEmpty()) {
                // 1. 取出栈顶元素
                NodeDepth current = stack.pop();
                TreeNode curNode = current.node;
                int curDepth = current.depth;
                // 2. 更新最大深度
                if (curDepth > maxDepth) {
                    maxDepth = curDepth;
                }
                // 3. 如果有右子节点，先把（右子节点, depth＋1）压栈
                if (curNode.right != null) {
                    stack.push(new NodeDepth(curNode.right, curDepth + 1));
                }
                /*
                 * 4. 如果有左子节点，再把（左子节点, depth＋1）压栈
                 * （注意：这样做是先遍历右子树，再遍历左子树，与递归的“先左后右”不同，
                 * 但只要把所有节点都访问到，最终计算的最大深度是一样的）
                 * */
                if (curNode.left != null) {
                    stack.push(new NodeDepth(curNode.left, curDepth + 1));
                }
            }
            return maxDepth;
        }
    }

    /**
     * BFS
     */
    class Solution3 {
        // 内部类：用来在栈中同时保存节点和该节点对应的深度
        class NodeDepth {
            TreeNode node;
            int depth;

            public NodeDepth(TreeNode node, int depth) {
                this.node = node;
                this.depth = depth;
            }
        }

        public int maxDepth(TreeNode root) {
            // 如果传入的根节点为空，直接返回 0
            if (root == null) {
                return 0;
            }
            int maxDepth = 0;
            // 用 Queue 保存“节点 + 深度”对
            Queue<NodeDepth> queue = new LinkedList<>();
            // 从根节点开始，初始深度为 1
            queue.offer(new NodeDepth(root, 1));
            while (!queue.isEmpty()) {
                // 1. 取出栈顶元素
                NodeDepth current = queue.poll();
                TreeNode curNode = current.node;
                int curDepth = current.depth;
                // 2. 更新最大深度
                if (curDepth > maxDepth) {
                    maxDepth = curDepth;
                }
                /*
                 * 3. 如果有左子节点，再把（左子节点, depth＋1）入队
                 * */
                if (curNode.left != null) {
                    queue.offer(new NodeDepth(curNode.left, curDepth + 1));
                }
                // 4. 如果有右子节点，先把（右子节点, depth＋1）入队
                if (curNode.right != null) {
                    queue.offer(new NodeDepth(curNode.right, curDepth + 1));
                }

            }
            return maxDepth;
        }
    }

}
