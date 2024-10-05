import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreeIntegerSum {

    @Test
    public void test() {
        assertEquals(List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)), threeSumMap(new int[]{-1, 0, 1, 2, -1, -4}));
        assertEquals(List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)), threeSumTwoPointers(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    private List<List<Integer>> threeSumTwoPointers(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                }
            }
        }
        return result;
    }

    private List<List<Integer>> threeSumMap(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            count.put(nums[i], count.get(nums[i]) - 1);
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length; j++) {
                count.put(nums[j], count.get(nums[j]) - 1);
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int target = -(nums[i] + nums[j]);
                if (count.getOrDefault(target, 0) > 0) {
                    result.add(Arrays.asList(nums[i], nums[j], target));
                }
            }
            for (int j = i + 1; j < nums.length; j++) {
                count.put(nums[j], count.get(nums[j]) + 1);
            }
        }
        return result;
    }
}
