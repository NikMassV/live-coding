package neetcode.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombinationSum {

    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(List.of(List.of(2, 2, 5), List.of(9)), solution.combinationSum(new int[]{2, 5, 6, 9}, 9));
        assertEquals(List.of(), solution.combinationSum(new int[]{4}, 7));
    }

    private class Solution {

        List<List<Integer>> result;

        private List<List<Integer>> combinationSum(int[] nums, int target) {
            result = new ArrayList<>();
            Arrays.sort(nums);
            dfs(0, new ArrayList<>(), 0, nums, target);
            return result;
        }

        private void dfs(int i, List<Integer> current, int total, int[] nums, int target) {
            if (total == target) {
                result.add(new ArrayList<>(current));
                return;
            }
            for (int j = i; j < nums.length; j++) {
                if (total + nums[j] > target) {
                    return;
                }
                current.add(nums[j]);
                dfs(j, current, total + nums[j], nums, target);
                current.remove(current.size() - 1);
            }
        }
    }
}
