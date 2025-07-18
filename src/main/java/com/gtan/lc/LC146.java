package com.gtan.lc;

import java.util.HashMap;

/**
 * <a href="https://leetcode.cn/problems/lru-cache/description/">LeetCode 146. LRU 缓存</a>
 *
 * @author gangtann@126.com
 * @version 1.1
 * @since 2025-06-02
 */
public class LC146 {

    static class LRUCache {

        // 双向链表节点类
        private class Node {
            int key;
            int value;
            Node prev;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        // 容量
        private int capacity;
        // key -> Node 的映射
        private HashMap<Integer, Node> cache;
        // 虚拟头节点
        private Node head;
        // 虚拟尾节点
        private Node tail;

        /**
         * 初始化LRU缓存
         *
         * @param capacity 缓存容量
         */
        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>();

            // 初始化虚拟头尾节点
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 获取缓存值
         *
         * @param key 键
         * @return 值，如果不存在返回-1
         */
        public int get(int key) {
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                // 移动到头部表示最近使用
                moveToHead(node);
                return node.value;
            }
            return -1;
        }

        /**
         * 添加或更新缓存
         *
         * @param key   键
         * @param value 值
         */
        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                // 如果已存在，更新值并移动到头部
                Node node = cache.get(key);
                node.value = value;
                moveToHead(node);
            } else {
                // 如果是新节点
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                addToHead(newNode);

                // 如果超出容量，移除最久未使用的节点
                if (cache.size() > capacity) {
                    Node tail = removeTail();
                    cache.remove(tail.key);
                }
            }
        }

        /**
         * 将节点移动到链表头部（表示最近使用）
         */
        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }

        /**
         * 添加节点到链表头部
         */
        private void addToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        /**
         * 从链表中移除节点
         */
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        /**
         * 移除链表尾部节点（最久未使用）
         */
        private Node removeTail() {
            Node res = tail.prev;
            removeNode(res);
            return res;
        }

        /**
         * 打印当前缓存状态（调试用）
         */
        public void printCache() {
            System.out.print("Cache: ");
            Node current = head.next;
            while (current != tail) {
                System.out.print("[" + current.key + ":" + current.value + "] ");
                current = current.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== LRU Cache 测试 ===");

        // 测试用例1：基本操作
        System.out.println("\n测试用例1：基本操作");
        LRUCache cache1 = new LRUCache(2);
        cache1.put(1, 1);
        cache1.printCache(); // [1:1]
        cache1.put(2, 2);
        cache1.printCache(); // [2:2] [1:1]
        System.out.println("get(1): " + cache1.get(1)); // 返回 1
        cache1.printCache(); // [1:1] [2:2]
        cache1.put(3, 3);    // 该操作会使得关键字 2 作废
        cache1.printCache(); // [3:3] [1:1]
        System.out.println("get(2): " + cache1.get(2)); // 返回 -1 (未找到)
        cache1.put(4, 4);    // 该操作会使得关键字 1 作废
        cache1.printCache(); // [4:4] [3:3]
        System.out.println("get(1): " + cache1.get(1)); // 返回 -1 (未找到)
        System.out.println("get(3): " + cache1.get(3)); // 返回 3
        System.out.println("get(4): " + cache1.get(4)); // 返回 4

        // 测试用例2：更新已存在key
        System.out.println("\n测试用例2：更新已存在key");
        LRUCache cache2 = new LRUCache(2);
        cache2.put(2, 1);
        cache2.printCache(); // [2:1]
        cache2.put(2, 2);
        cache2.printCache(); // [2:2]
        System.out.println("get(2): " + cache2.get(2)); // 返回 2
        cache2.put(1, 1);
        cache2.printCache(); // [1:1] [2:2]
        cache2.put(4, 1);
        cache2.printCache(); // [4:1] [1:1]
        System.out.println("get(2): " + cache2.get(2)); // 返回 -1

        // 测试用例3：容量为1
        System.out.println("\n测试用例3：容量为1");
        LRUCache cache3 = new LRUCache(1);
        cache3.put(2, 1);
        cache3.printCache(); // [2:1]
        System.out.println("get(2): " + cache3.get(2)); // 返回 1
        cache3.put(3, 2);
        cache3.printCache(); // [3:2]
        System.out.println("get(2): " + cache3.get(2)); // 返回 -1
        System.out.println("get(3): " + cache3.get(3)); // 返回 2

        // 测试用例4：LeetCode示例
        System.out.println("\n测试用例4：LeetCode官方示例");
        LRUCache cache4 = new LRUCache(2);
        cache4.put(1, 1);
        cache4.put(2, 2);
        System.out.println("get(1): " + cache4.get(1));    // 返回 1
        cache4.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println("get(2): " + cache4.get(2));    // 返回 -1 (未找到)
        cache4.put(4, 4);    // 该操作会使得关键字 1 作废
        System.out.println("get(1): " + cache4.get(1));    // 返回 -1 (未找到)
        System.out.println("get(3): " + cache4.get(3));    // 返回 3
        System.out.println("get(4): " + cache4.get(4));    // 返回 4
    }

}
