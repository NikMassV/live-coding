package neetcode.twopointers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TwoIntegerSum2 {

    @Test
    public void test() {
        assertArrayEquals(new int[]{1, 2}, twoSum(new int[]{1, 2, 3, 4}, 3));
    }

    private int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int curSum = numbers[l] + numbers[r];
            if (curSum > target) {
                r--;
            } else if (curSum < target) {
                l++;
            } else {
                return new int[]{l + 1, r + 1};
            }
        }
        return new int[0];
    }
}
