import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveDuplicatesFromSortedArray {

    @Test
    public void test() {
        assertEquals(6, removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 1}));
    }

    private int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i - 1]) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
