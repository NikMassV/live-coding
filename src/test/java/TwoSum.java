//https://leetcode.com/problems/two-sum/solutions/4288318/0ms-array-hash/

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TwoSum {

    @Test
    public void test() {
        assertArrayEquals(new int[]{2, 4}, twoSum(new int[]{1, 2, 3, 4, 5}, 8));
    }

    public int[] twoSum(int[] nums, int target) {
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
}
