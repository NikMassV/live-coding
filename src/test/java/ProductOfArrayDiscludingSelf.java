import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductOfArrayDiscludingSelf {

    @Test
    public void test() {
        assertArrayEquals(new int[]{48, 24, 12, 8}, productExceptSelf(new int[]{1, 2, 4, 6}));
        assertArrayEquals(new int[]{0, -6, 0, 0, 0}, productExceptSelf(new int[]{-1, 0, 1, 2, 3}));
    }

    private int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int prod = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    prod *= nums[j];
                }
            }
            res[i] = prod;
        }
        return res;
    }
}
