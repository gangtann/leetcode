package com.gtan.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-05
 * @Description: 39. 组合总和
 * @Version: 1.0
 */
public class LC39 {

    class Solution {
        private List<List<Integer>> ans = new ArrayList<>();
        private List<Integer> path = new ArrayList<>();
        private int[] nums;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            // 排序是为了剪枝，如果不剪枝则不需要排序
            Arrays.sort(candidates);
            this.nums = candidates;
            dfs(0, target);
            return ans;
        }

        private void dfs(int i, int target) {
            /*
             * 边界条件:
             *          target == 0 表示找到了和为target的组合
             *          target < 0 表示后续的递归过程中不可能找到和为target的组合
             * */
            if (target == 0) {
                ans.add(new ArrayList<>(path));
                return;
            } else if (target < 0) {
                return;
            }

            /*
            * for循环横向遍历, dfs纵向遍历
            * 因为可以重复选取, 所以递归遍历时传入的是当前位置的索引j而不是j+1
            * 剪枝: 横向遍历时因为数组有序, 如果target - nums[j] < 0则后续也不可能找到和为target的组合
            * */
            for (int j = i; j < nums.length; j++) {
                if (target - nums[j] < 0) {
                    return;
                }
                path.add(nums[j]);
                dfs(j, target - nums[j]);
                path.remove(path.size() - 1);
            }
        }

    }

}
