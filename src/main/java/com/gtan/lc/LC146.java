package com.gtan.lc;

import java.util.HashMap;

/**
*@Author: GangTan
*@CreateTime: 2025-06-02
*@Description: 146. LRU 缓存
*@Version: 1.0
*/
public class LC146 {

    class LRUCache {

        // 双向链表节点
        class Node {
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
        // 当前缓存中的节点数
        private int size;
        // key -> Node 的映射
        private HashMap<Integer, Node> map;
        // 哑结点：head.next 为最近使用，tail.prev 为最久未使用
        private Node head;
        private Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            map = new HashMap<>();
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) {
                return -1;
            }
            // 已经访问：移到链表头部
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                // 如果已存在，只更新 value 并移到头部
                node.value = value;
                moveToHead(node);
            } else {
                // 不存在则新建节点，插入到头部
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addToHead(newNode);
                size++;
                // 如果超过容量，就删除尾部最久未使用的节点
                if (size > capacity) {
                    Node toRemove = tail.prev;
                    removeNode(toRemove);
                    map.remove(toRemove.key);
                    size--;
                }
            }
        }

        // 把已有节点先摘除，再插到 head 之后
        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }

        // 将 node 插入到 head 之后（作为最近使用）
        private void addToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        // 将 node 从链表中摘除
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

}
