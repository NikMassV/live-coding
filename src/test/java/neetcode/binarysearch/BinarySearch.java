package neetcode.binarysearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearch {

    @Test
    public void test() {
        assertEquals(3, search(new int[]{-1, 0, 2, 4, 6, 8}, 4));
        assertEquals(-1, search(new int[]{-1, 0, 2, 4, 6, 8}, 3));
    }

    private int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = l + ((r - l) / 2);
            if (nums[m] > target) {
                r = m - 1;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }
}
