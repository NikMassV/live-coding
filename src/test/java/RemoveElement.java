import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveElement {

    @Test
    public void test() {
        assertEquals(2, removeElement(new int[]{3, 2, 2, 3}, 3));
        assertEquals(5, removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }

    private int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
