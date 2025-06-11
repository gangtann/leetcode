package com.gtan.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-07
 * @Description: 118. 杨辉三角
 * @Version: 1.0
 */
public class LC118 {

    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(List.of(1));
            for (int i = 1; i < numRows; i++) {
                List<Integer> row = new ArrayList<>();
                row.add(1);
                for (int j = 1; j < i; j++) {
                    row.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
                row.add(1);
                ans.add(row);
            }
            return ans;
        }
    }

}
