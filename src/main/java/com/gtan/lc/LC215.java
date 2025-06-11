package com.gtan.lc;

import java.util.PriorityQueue;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-02
 * @Description: 215. 数组中的第K个最大元素
 * @Version: 1.0
 */
public class LC215 {

    class Solution {
        /**
         * 返回数组 nums 中第 k 大的元素
         */
        public int findKthLargest(int[] nums, int k) {
            // Java 默认的 PriorityQueue 是小根堆，堆顶元素是最小值
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for (int num : nums) {
                if (minHeap.size() < k) {
                    // 如果堆中元素个数还不到 k，直接插入
                    minHeap.offer(num);
                } else if (num > minHeap.peek()) {
                    // 否则，当 num 比当前堆顶（即 k 个数中的最小值）还大时，先弹出堆顶再插入 num
                    minHeap.poll();
                    minHeap.offer(num);
                }
            }
            // 经过上述处理，堆里正好保留了最大的 k 个数，堆顶即第 k 大
            return minHeap.peek();
        }
    }



}
