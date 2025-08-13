package codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximumSubarraySum {

    @Test
    public void testEmptyArray() throws Exception {
        assertEquals(0, sequence(new int[]{}));
    }
    @Test
    public void testExampleArray() throws Exception {
        assertEquals(6, sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public static int sequence(int[] arr) {
        int maxSum = 0;
        int currentSum = 0;
        for (int x : arr) {
            currentSum += x;
            if (currentSum < 0) {
                currentSum = 0;
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum;
    }
}
