package codewars;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumOfArraySinlges {

    @Test
    public void test() {
        assertEquals(15, repeats(new int[]{4, 5, 7, 5, 4, 8}));
        assertEquals(19, repeats(new int[]{9, 10, 19, 13, 19, 13}));
        assertEquals(12, repeats(new int[]{16, 0, 11, 4, 8, 16, 0, 11}));
    }

    private int repeats(int[] arr) {
        List<Integer> list = Arrays.stream(arr).boxed().toList();
        return Arrays.stream(arr).filter(el -> Collections.frequency(list, el) == 1).sum();
    }
}
