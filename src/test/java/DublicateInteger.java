import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DublicateInteger {

    @Test
    public void test() {
        assertTrue(hasDuplicate(new int[] {1, 2, 3, 4, 3}));
        assertFalse(hasDuplicate(new int[] {1, 2, 3, 4}));
    }

    private boolean hasDuplicate(int[] nums) {
        Set<Integer> uniques = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (uniques.contains(nums[i])) {
                return true;
            }
            uniques.add(nums[i]);
        }
        return false;
    }
}
