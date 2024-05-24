import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchInsertPosition {

    @Test
    public void test() {
        assertEquals(2, searchInsert(new int[] {1,3,5,6}, 5));
        assertEquals(1, searchInsert(new int[] {1,3,5,6}, 2));
        assertEquals(4, searchInsert(new int[] {1,3,5,6}, 7));
    }

    private int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}
