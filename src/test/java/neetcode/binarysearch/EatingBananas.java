package neetcode.binarysearch;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EatingBananas {

    @Test
    public void test() {
        assertEquals(25, minEatingSpeed(new int[]{25, 10, 23, 4}, 4));
    }

    private int minEatingSpeed(int[] piles, int h) {
        int l = 1;
        int r = Arrays.stream(piles).max().getAsInt();
        int result = r;
        while (l <= r) {
            int k = (l + r) / 2;
            long totalTime = 0;
            for (int p : piles) {
                totalTime += (long) Math.ceil((double) p / k);
            }
            if (totalTime <= h) {
                result = k;
                r = k - 1;
            } else {
                l = k + 1;
            }
        }
        return result;
    }
}
