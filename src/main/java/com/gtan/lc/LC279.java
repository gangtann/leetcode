package com.gtan.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: GangTan
 * @CreateTime: 2025-06-07
 * @Description: 279. 完全平方数
 * @Version: 1.0
 */
public class LC279 {

    class Solution {
        public int numSquares(int n) {
            List<Integer> squares = new ArrayList<>();
            for (int i = 1; i <= Math.sqrt(n); i++) {
                squares.add(i * i);
            }
            int[] dp = new int[n + 1];
            Arrays.fill(dp, n + 1);
            dp[0] = 0;
            for (int i = 1; i <= n; i++) {
                for (int square : squares) {
                    if (i - square >= 0) {
                        dp[i] = Math.min(dp[i], dp[i - square] + 1);
                    }
                }
            }
            return dp[n];
        }
    }

}
