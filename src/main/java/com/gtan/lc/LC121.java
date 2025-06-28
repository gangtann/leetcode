package com.gtan.lc;

/**
 * @Author: gangtann@126.com
 * @CreateTime: 2025-06-28
 * @Description: 121. 买卖股票的最佳时机
 * @Version: 1.0
 */
public class LC121 {

    class Solution {
        /**
         * 思路：一次遍历股票价格数组，维护两个变量：
         *   1. buyPrice — 当前遍历过的最低买入价
         *   2. ans — 当前能获得的最大利润
         * 每到一天 i，先判断 prices[i] 是否低于 buyPrice，若是则更新买入价；
         * 否则计算当天卖出可获利润 prices[i] - buyPrice，并与 ans 比较更新最大值。
         */
        public int maxProfit(int[] prices) {
            // 初始化买入价为第一天价格
            int buyPrice = prices[0];
            // 初始化最大利润为 0
            int ans = 0;
            // 从第二天开始遍历
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] < buyPrice) {
                    // 如果当前价格更低，更新买入价
                    buyPrice = prices[i];
                } else {
                    // 否则计算当前卖出的利润
                    int profit = prices[i] - buyPrice;
                    // 更新最大利润
                    ans = Math.max(ans, profit);
                }
            }
            // 返回最终可获得的最大利润
            return ans;
        }
    }


}
