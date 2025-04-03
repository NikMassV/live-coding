package neetcode.arrayshashing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainsDuplicate {

    @Test
    public void testForSolution() {
        assertTrue(containsDuplicateFor(new int[]{1, 2, 1}));
        assertFalse(containsDuplicateFor(new int[]{1, 2, 3}));
    }

    @Test
    public void testStreamSolutionHashSet() {
        assertTrue(containsDuplicateHashSet(new int[]{1, 2, 1}));
        assertFalse(containsDuplicateHashSet(new int[]{1, 2, 3}));
    }

    @Test
    public void testStreamSolution() {
        assertTrue(containsDuplicateStream(new int[]{1, 2, 1}));
        assertFalse(containsDuplicateStream(new int[]{1, 2, 3}));
    }

    private boolean containsDuplicateFor(int[] nums) { //O(nlogn)
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    private boolean containsDuplicateHashSet(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    private boolean containsDuplicateStream(int[] nums) { //O(n)
        return Arrays.stream(nums).distinct().count() < nums.length;
    }
}
