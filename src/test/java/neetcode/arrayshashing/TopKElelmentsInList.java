package neetcode.arrayshashing;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TopKElelmentsInList {

    @Test
    public void test() {
        int[] actual = topKFrequent(new int[]{1, 2, 2, 3, 3, 3}, 2);
        Arrays.sort(actual);
        assertArrayEquals(new int[]{2, 3}, actual);
    }

    private int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        List<Integer>[] freq = new List[nums.length + 1];
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }
        for (int n : nums) {
            counter.put(n, counter.getOrDefault(n, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            freq[entry.getValue()].add(entry.getKey());
        }
        int[] res = new int[k];
        int index = 0;
        for (int i = freq.length - 1; i > 0 && index < k; i--) {
            for (int n : freq[i]) {
                res[index++] = n;
                if (index == k) {
                    return res;
                }
            }
        }
        return res;
    }
}
