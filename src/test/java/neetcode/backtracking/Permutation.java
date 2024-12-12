package neetcode.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Permutation {

    @Test
    public void test() {
        assertEquals(List.of(List.of(3, 2, 1), List.of(2, 3, 1), List.of(2, 1, 3), List.of(3, 1, 2), List.of(1, 3, 2), List.of(1, 2, 3)),
                permute(new int[]{1, 2, 3}));
    }

    private List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> perms = new ArrayList<>();
        perms.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> new_perms = new ArrayList<>();
            for (List<Integer> p : perms) {
                for (int i = 0; i <= p.size(); i++) {
                    List<Integer> p_copy = new ArrayList<>(p);
                    p_copy.add(i, num);
                    new_perms.add(p_copy);
                }
            }
            perms = new_perms;
        }
        return perms;
    }
}
