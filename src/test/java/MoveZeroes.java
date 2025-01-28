import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MoveZeroes {

    @Test
    public void test() {
        assertArrayEquals(new int[]{1, 3, 12, 0, 0}, moveZeroes(new int[]{0, 1, 0, 3, 12}));
        assertArrayEquals(new int[]{0}, moveZeroes(new int[]{0}));
    }

    public int[] moveZeroes(int[] nums) {
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
                left++;
            }
        }
        return nums;
    }
}
