package neetcode.arrayshashing;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestConsecutiveSequence {

    @Test
    public void test() {
        assertEquals(4, longestConsecutive(new int[]{2, 20, 4, 10, 3, 4, 5}));
        assertEquals(7, longestConsecutive(new int[]{0, 3, 2, 5, 4, 6, 1, 1}));

    }

    private int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int longest = 0;
        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int length = 1;
                while (numSet.contains(num + length)) {
                    length++;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }
}
