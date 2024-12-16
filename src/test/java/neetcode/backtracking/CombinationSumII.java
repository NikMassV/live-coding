package neetcode.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombinationSumII {

    @Test
    public void test() {
        Solution solution = new Solution();
        List<List<Integer>> expected = List.of(
                List.of(1, 2, 5),
                List.of(2, 2, 4),
                List.of(2, 6)
        );
        assertEquals(expected, solution.combinationSum2(new int[]{9, 2, 2, 4, 6, 1, 5}, 8));
    }

    private class Solution {

        private List<List<Integer>> res;

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            res = new ArrayList<>();
            Arrays.sort(candidates);
            dfs(candidates, target, 0, new ArrayList<>(), 0);
            return res;
        }

        private void dfs(int[] candidates, int target, int i, List<Integer> cur, int total) {
            if (total == target) {
                res.add(new ArrayList<>(cur));
                return;
            }
            if (total > target || i == candidates.length) {
                return;
            }
            cur.add(candidates[i]);
            dfs(candidates, target, i + 1, cur, total + candidates[i]);
            cur.remove(cur.size() - 1);
            while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
                i++;
            }
            dfs(candidates, target, i + 1, cur, total);
        }
    }
}
