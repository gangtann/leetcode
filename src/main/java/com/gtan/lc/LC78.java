package com.gtan.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-03
 * @Description: 78. 子集
 * @Version: 1.0
 */
public class LC78 {

    class Solution {
        private List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        private int[] nums;

        public List<List<Integer>> subsets(int[] nums) {
            this.nums = nums;
            dfs(0);
            return ans;
        }

        private void dfs(int idx) {
            ans.add(new ArrayList<>(path));
            for (int i = idx; i < nums.length; i++) {
                path.add(nums[i]);
                dfs(i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        new LC78().new Solution().subsets(new int[]{1, 2, 3});
    }

}
