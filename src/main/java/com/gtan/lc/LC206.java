package com.gtan.lc;

/**
 * <a href="https://leetcode.cn/problems/reverse-linked-list/description/">LeetCode 206. 反转链表</a>
 *
 * @author gangtann@126.com
 * @version 1.0
 * @since 2025-07-18
 */
public class LC206 {

    static class ReverseLinkedList {

        // 链表节点定义
        public static class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                ListNode current = this;
                while (current != null) {
                    sb.append(current.val);
                    if (current.next != null) {
                        sb.append(" -> ");
                    }
                    current = current.next;
                }
                return sb.toString();
            }
        }

        /**
         * 方法1：迭代法（双指针）
         * 时间复杂度：O(n) - 遍历整个链表一次
         * 空间复杂度：O(1) - 只使用了常数个额外空间
         */
        public ListNode reverseListIterative(ListNode head) {
            // 前一个节点
            ListNode prev = null;
            // 当前节点
            ListNode current = head;

            while (current != null) {
                // 暂存下一个节点
                ListNode nextTemp = current.next;
                // 反转当前节点的指向
                current.next = prev;
                // prev前移
                prev = current;
                // current前移
                current = nextTemp;
            }

            // prev成为新的头节点
            return prev;
        }

        /**
         * 方法2：递归法
         * 时间复杂度：O(n) - 需要遍历整个链表
         * 空间复杂度：O(n) - 递归调用栈的深度
         */
        public ListNode reverseListRecursive(ListNode head) {
            // 递归终止条件：空链表或只有一个节点
            if (head == null || head.next == null) {
                return head;
            }

            // 递归反转剩余的链表
            ListNode reverseNode = reverseListRecursive(head.next);

            // 将当前节点的下一个节点的next指向当前节点
            head.next.next = head;

            // 将当前节点的next置为null，避免循环
            head.next = null;

            return reverseNode;
        }

        /**
         * 方法3：头插法（创建新链表）
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         */
        public ListNode reverseListHeadInsert(ListNode head) {
            ListNode newHead = null;
            ListNode current = head;

            while (current != null) {
                ListNode next = current.next;
                current.next = newHead;
                newHead = current;
                current = next;
            }

            return newHead;
        }

        /**
         * 辅助方法：从数组创建链表
         */
        public static ListNode createList(int[] values) {
            if (values == null || values.length == 0) {
                return null;
            }

            ListNode dummy = new ListNode(0);
            ListNode current = dummy;

            for (int val : values) {
                current.next = new ListNode(val);
                current = current.next;
            }

            return dummy.next;
        }

        /**
         * 辅助方法：比较两个链表是否相等
         */
        public static boolean areEqual(ListNode l1, ListNode l2) {
            while (l1 != null && l2 != null) {
                if (l1.val != l2.val) {
                    return false;
                }
                l1 = l1.next;
                l2 = l2.next;
            }
            return l1 == null && l2 == null;
        }

        public static void main(String[] args) {
            ReverseLinkedList solution = new ReverseLinkedList();

            System.out.println("=== 反转链表测试 ===");

            // 测试用例1：正常链表
            System.out.println("\n测试用例1：1->2->3->4->5");
            ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
            System.out.println("原始链表：" + head1);

            ListNode reversed1 = solution.reverseListIterative(head1);
            System.out.println("迭代反转后：" + reversed1);

            ListNode head2 = createList(new int[]{1, 2, 3, 4, 5});
            ListNode reversed2 = solution.reverseListRecursive(head2);
            System.out.println("递归反转后：" + reversed2);

            ListNode head3 = createList(new int[]{1, 2, 3, 4, 5});
            ListNode reversed3 = solution.reverseListHeadInsert(head3);
            System.out.println("头插法反转后：" + reversed3);

            // 测试用例2：单节点链表
            System.out.println("\n测试用例2：单节点链表");
            ListNode single = createList(new int[]{1});
            System.out.println("原始链表：" + single);
            System.out.println("迭代反转后：" + solution.reverseListIterative(single));

            // 测试用例3：空链表
            System.out.println("\n测试用例3：空链表");
            ListNode empty = createList(new int[]{});
            System.out.println("原始链表：" + empty);
            System.out.println("反转后：" + solution.reverseListIterative(empty));

            // 测试用例4：两个节点
            System.out.println("\n测试用例4：1->2");
            ListNode twoNodes = createList(new int[]{1, 2});
            System.out.println("原始链表：" + twoNodes);
            System.out.println("反转后：" + solution.reverseListIterative(twoNodes));

            // 验证所有方法结果一致性
            System.out.println("\n=== 验证所有方法结果一致性 ===");
            int[][] testCases = {
                    {1, 2, 3, 4, 5},
                    {1, 1, 2, 1},
                    {5, 4, 3, 2, 1},
                    {1},
                    {}
            };

            for (int[] testCase : testCases) {
                ListNode original = createList(testCase);
                ListNode expected = createList(reverseArray(testCase));

                ListNode iterative = solution.reverseListIterative(createList(testCase));
                ListNode recursive = solution.reverseListRecursive(createList(testCase));
                ListNode headInsert = solution.reverseListHeadInsert(createList(testCase));

                boolean allMatch = areEqual(iterative, expected) &&
                        areEqual(recursive, expected) &&
                        areEqual(headInsert, expected);

                System.out.printf("测试用例 %s: %s%n",
                        java.util.Arrays.toString(testCase),
                        allMatch ? "通过" : "失败");
            }
        }

        /**
         * 辅助方法：反转数组
         */
        private static int[] reverseArray(int[] arr) {
            if (arr == null || arr.length == 0) {
                return arr;
            }
            int[] reversed = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                reversed[i] = arr[arr.length - 1 - i];
            }
            return reversed;
        }
    }

}
