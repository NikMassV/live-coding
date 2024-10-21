package neetcode.arrayshashing;//https://leetcode.com/problems/two-sum/solutions/4288318/0ms-array-hash/

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TwoSum {

    @Test
    public void test() {
        assertArrayEquals(new int[]{2, 4}, twoSum(new int[]{1, 2, 3, 4, 5}, 8));
        assertArrayEquals(new int[]{2, 3}, twoSum1(new int[]{1, 2, 3, 4, 5}, 7));
    }

    private int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int requiredNumber = target - nums[i];
            if (map.containsKey(requiredNumber) && map.get(requiredNumber) != i) {
                return new int[]{i, map.get(requiredNumber)};
            }
        }
        throw new IllegalArgumentException("No solution for defined input data!");
    }

    public int[] twoSum1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int diff = target - num;
            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            }
            map.put(num, i);
        }
        return new int[]{};
    }
}
