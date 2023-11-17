import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainsDuplicate {

    @Test
    public void test() {
        assertTrue(containsDuplicate(new int[] {1, 2, 1}));
        assertFalse(containsDuplicate(new int[] {1, 2, 3}));
    }

    private boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);

        for(int i=0; i<nums.length - 1; i++) {
            if(nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
