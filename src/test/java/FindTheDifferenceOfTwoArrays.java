import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTheDifferenceOfTwoArrays {

    @Test
    public void test() {
        assertEquals(List.of(List.of(1, 3), List.of(4, 6)), findDifference(new int[] {1, 2, 3}, new int[] {2, 4, 6}));
    }

    private List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Integer[] boxedNums1 = Arrays.stream(nums1).boxed().toArray(Integer[]::new);
        Integer[] boxedNums2 = Arrays.stream(nums2).boxed().toArray(Integer[]::new);
        Set<Integer> s1 = new HashSet<>(Arrays.asList(boxedNums1));
        Set<Integer> s2 = new HashSet<>(Arrays.asList(boxedNums2));
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());
        for (int i : s1) {
            if (!s2.contains(i)) {
                ans.get(0).add(i);
            }
        }
        for (int i : s2) {
            if (!s1.contains(i)) {
                ans.get(1).add(i);
            }
        }
        return ans;
    }
}
