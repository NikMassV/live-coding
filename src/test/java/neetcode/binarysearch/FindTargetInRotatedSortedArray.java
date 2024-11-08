package neetcode.binarysearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTargetInRotatedSortedArray {

    @Test
    public void test() {
        assertEquals(4, search(new int[]{3, 4, 5, 6, 1, 2}, 1));
        assertEquals(5, search(new int[]{3, 4, 5, 6, 1, 2}, 2));
        assertEquals(-1, search(new int[]{3, 5, 6, 0, 1, 2}, 4));
    }

    private int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[l] <= nums[mid]) {
                if (target > nums[mid] || target < nums[l]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                if (target < nums[mid] || target > nums[r]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return -1;
    }
}
