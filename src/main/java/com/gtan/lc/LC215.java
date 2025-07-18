package com.gtan.lc;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * <a href="https://leetcode.cn/problems/kth-largest-element-in-an-array/description/">LeetCode 215. 数组中的第K个最大元素</a>
 *
 * @author gangtann@126.com
 * @version 1.1
 * @since 2025-06-02
 */
public class LC215 {

    static class KthLargestElement {

        /**
         * 方法1：排序法
         * 时间复杂度：O(n log n)
         * 空间复杂度：O(1) 或 O(n)（取决于排序实现）
         */
        public int findKthLargestSort(int[] nums, int k) {
            Arrays.sort(nums);
            return nums[nums.length - k];
        }

        /**
         * 方法2：最小堆（优先队列）
         * 时间复杂度：O(n log k)
         * 空间复杂度：O(k)
         */
        public int findKthLargestHeap(int[] nums, int k) {
            // 创建最小堆，堆的大小为k
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

        /**
         * 方法3：快速选择算法（基于快速排序分区）
         * 时间复杂度：平均O(n)，最坏O(n^2)
         * 空间复杂度：O(1)
         */
        public int findKthLargestQuickSelect(int[] nums, int k) {
            int left = 0;
            int right = nums.length - 1;
            int targetIndex = nums.length - k;  // 第k大元素的索引

            Random random = new Random();

            while (left <= right) {
                // 随机选择pivot
                int pivotIndex = left + random.nextInt(right - left + 1);
                int pivotNewIndex = partition(nums, left, right, pivotIndex);

                if (pivotNewIndex == targetIndex) {
                    return nums[pivotNewIndex];
                } else if (pivotNewIndex < targetIndex) {
                    left = pivotNewIndex + 1;
                } else {
                    right = pivotNewIndex - 1;
                }
            }

            return -1; // 不应该到达这里
        }

        /**
         * 快速选择的分区函数
         */
        private int partition(int[] nums, int left, int right, int pivotIndex) {
            int pivotValue = nums[pivotIndex];

            // 将pivot移到末尾
            swap(nums, pivotIndex, right);

            int storeIndex = left;

            // 分区操作：小于pivot的元素移到左边
            for (int i = left; i < right; i++) {
                if (nums[i] < pivotValue) {
                    swap(nums, storeIndex, i);
                    storeIndex++;
                }
            }

            // 将pivot移到最终位置
            swap(nums, storeIndex, right);

            return storeIndex;
        }

        /**
         * 交换数组中的两个元素
         */
        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        /**
         * 方法4：改进的快速选择（三数取中法选择pivot）
         * 时间复杂度：平均O(n)，最坏O(n^2)
         * 空间复杂度：O(1)
         */
        public int findKthLargestQuickSelectOptimized(int[] nums, int k) {
            int n = nums.length;
            int left = 0, right = n - 1;
            int targetIndex = n - k;

            while (left <= right) {
                int pivotIndex = partitionMedianOfThree(nums, left, right);

                if (pivotIndex == targetIndex) {
                    return nums[pivotIndex];
                } else if (pivotIndex < targetIndex) {
                    left = pivotIndex + 1;
                } else {
                    right = pivotIndex - 1;
                }
            }

            return -1;
        }

        /**
         * 三数取中法选择pivot的分区
         */
        private int partitionMedianOfThree(int[] nums, int left, int right) {
            int mid = left + (right - left) / 2;

            // 三数取中
            if (nums[left] > nums[mid]) {
                swap(nums, left, mid);
            }
            if (nums[left] > nums[right]) {
                swap(nums, left, right);
            }
            if (nums[mid] > nums[right]) {
                swap(nums, mid, right);
            }

            int pivot = nums[mid];
            swap(nums, mid, right);

            int i = left;
            for (int j = left; j < right; j++) {
                if (nums[j] < pivot) {
                    swap(nums, i, j);
                    i++;
                }
            }
            swap(nums, i, right);
            return i;
        }

        public static void main(String[] args) {
            KthLargestElement solution = new KthLargestElement();

            System.out.println("=== 数组中的第K个最大元素测试 ===");

            // 测试用例1：LeetCode示例
            int[] nums1 = {3, 2, 1, 5, 6, 4};
            int k1 = 2;
            testSolution(solution, "测试用例1", nums1, k1, 5);

            // 测试用例2：LeetCode示例
            int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
            int k2 = 4;
            testSolution(solution, "测试用例2", nums2, k2, 4);

            // 测试用例3：边界情况
            int[] nums3 = {1};
            int k3 = 1;
            testSolution(solution, "测试用例3", nums3, k3, 1);

            // 测试用例4：重复元素
            int[] nums4 = {2, 2, 2, 2};
            int k4 = 2;
            testSolution(solution, "测试用例4", nums4, k4, 2);

            // 测试用例5：负数
            int[] nums5 = {-1, -2, -3, -4, -5};
            int k5 = 1;
            testSolution(solution, "测试用例5", nums5, k5, -1);

            // 性能测试
            System.out.println("\n=== 性能测试（大数组） ===");
            int[] largeArray = generateLargeArray(10000);
            int k6 = 5000;

            long startTime, endTime;

            // 测试排序法
            startTime = System.nanoTime();
            int result1 = solution.findKthLargestSort(largeArray.clone(), k6);
            endTime = System.nanoTime();
            System.out.printf("排序法耗时: %.2f ms, 结果: %d%n",
                    (endTime - startTime) / 1_000_000.0, result1);

            // 测试堆法
            startTime = System.nanoTime();
            int result2 = solution.findKthLargestHeap(largeArray.clone(), k6);
            endTime = System.nanoTime();
            System.out.printf("堆法耗时: %.2f ms, 结果: %d%n",
                    (endTime - startTime) / 1_000_000.0, result2);

            // 测试快速选择
            startTime = System.nanoTime();
            int result3 = solution.findKthLargestQuickSelect(largeArray.clone(), k6);
            endTime = System.nanoTime();
            System.out.printf("快速选择耗时: %.2f ms, 结果: %d%n",
                    (endTime - startTime) / 1_000_000.0, result3);

            // 测试优化后的快速选择
            startTime = System.nanoTime();
            int result4 = solution.findKthLargestQuickSelectOptimized(largeArray.clone(), k6);
            endTime = System.nanoTime();
            System.out.printf("优化后的快速选择耗时: %.2f ms, 结果: %d%n",
                    (endTime - startTime) / 1_000_000.0, result4);

            // 验证结果一致性
            System.out.println("\n=== 验证所有方法结果一致性 ===");
            int[][] testCases = {
                    {3, 2, 1, 5, 6, 4},
                    {3, 2, 3, 1, 2, 4, 5, 5, 6},
                    {1},
                    {2, 2, 2, 2},
                    {-1, -2, -3, -4, -5}
            };
            int[] ks = {2, 4, 1, 2, 1};

            for (int i = 0; i < testCases.length; i++) {
                int[] nums = testCases[i];
                int k = ks[i];

                int sortResult = solution.findKthLargestSort(nums.clone(), k);
                int heapResult = solution.findKthLargestHeap(nums.clone(), k);
                int quickSelectResult = solution.findKthLargestQuickSelect(nums.clone(), k);
                int optimizedResult = solution.findKthLargestQuickSelectOptimized(nums.clone(), k);

                boolean allEqual =
                        sortResult == heapResult && heapResult == quickSelectResult && quickSelectResult == optimizedResult;

                System.out.printf("测试用例 %d: %s, 结果: %d%n",
                        i + 1, allEqual ? "通过" : "失败", sortResult);
            }
        }

        /**
         * 测试解决方案
         */
        private static void testSolution(KthLargestElement solution, String testName,
                                         int[] nums, int k, int expected) {
            System.out.printf("\n%s: %s, k=%d\n", testName, java.util.Arrays.toString(nums), k);

            int result1 = solution.findKthLargestSort(nums.clone(), k);
            int result2 = solution.findKthLargestHeap(nums.clone(), k);
            int result3 = solution.findKthLargestQuickSelect(nums.clone(), k);
            int result4 = solution.findKthLargestQuickSelectOptimized(nums.clone(), k);

            System.out.printf("排序法结果: %d, 期望: %d, %s%n",
                    result1, expected, result1 == expected ? "通过" : "失败");
            System.out.printf("堆法结果: %d, 期望: %d, %s%n",
                    result2, expected, result2 == expected ? "通过" : "失败");
            System.out.printf("快速选择结果: %d, 期望: %d, %s%n",
                    result3, expected, result3 == expected ? "通过" : "失败");
            System.out.printf("优化快速选择结果: %d, 期望: %d, %s%n",
                    result4, expected, result4 == expected ? "通过" : "失败");
        }

        /**
         * 生成大数组用于性能测试
         */
        private static int[] generateLargeArray(int size) {
            Random random = new Random();
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(1000000);
            }
            return array;
        }
    }


}
