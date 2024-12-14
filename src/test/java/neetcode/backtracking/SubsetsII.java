package neetcode.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubsetsII {

    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(List.of(List.of(), List.of(1), List.of(1, 1), List.of(1, 1, 2), List.of(1, 2), List.of(2)), solution.subsetsWithDup(new int[]{1, 2, 1}));
    }

    private class Solution {

        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            backtrack(0, new ArrayList<>(), nums);
            return res;
        }

        private void backtrack(int i, List<Integer> subset, int[] nums) {
            res.add(new ArrayList<>(subset));
            for (int j = i; j < nums.length; j++) {
                if (j > i && nums[j] == nums[j - 1]) {
                    continue;
                }
                subset.add(nums[j]);
                backtrack(j + 1, subset, nums);
                subset.remove(subset.size() - 1);
            }
        }
    }
}
