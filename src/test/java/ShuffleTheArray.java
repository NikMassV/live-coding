import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ShuffleTheArray {

    @Test
    public void test() {
        assertArrayEquals(new int[]{2, 3, 5, 4, 1, 7}, shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3));
    }

    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[nums.length];
        int leftSubsetPointer = 0;
        int rightSubsetPointer = n;
        for (int i = 1; i < nums.length; i += 2) {
            result[i - 1] = nums[leftSubsetPointer++];
            result[i] = nums[rightSubsetPointer++];
        }
        return result;
    }
}
