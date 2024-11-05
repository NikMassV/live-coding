package neetcode.slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyAndSellCrypto {

    @Test
    public void test() {
        assertEquals(6, maxProfit(new int[]{10, 1, 5, 6, 7, 1}));
        assertEquals(0, maxProfit(new int[]{10, 8, 7, 5, 2}));
    }

    private int maxProfit(int[] prices) {
        int maxP = 0;
        int minBuy = prices[0];
        for (int sell : prices) {
            maxP = Math.max(maxP, sell - minBuy);
            minBuy = Math.min(minBuy, sell);
        }
        return maxP;
    }
}
