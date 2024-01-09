package codewars;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Bonuses {

    @Test
    public void test() {
        assertArrayEquals(new long[]{1860, 13640, 2728}, calculateBonus(new int[]{22, 3, 15}, 18228));
        assertArrayEquals(new long[]{10241, 5852, 7448}, calculateBonus(new int[]{8, 14, 11}, 23541));
        assertArrayEquals(new long[]{13515, 5406, 6360}, calculateBonus(new int[]{8, 20, 17}, 25281));
        assertArrayEquals(new long[]{13340, 5336, 3480}, calculateBonus(new int[]{6, 15, 23}, 22156));
        assertArrayEquals(new long[]{4608, 3584, 2688}, calculateBonus(new int[]{7, 9, 12}, 10880));
    }

    private long[] calculateBonus(int[] arr, long s) {
        double total = Arrays.stream(arr).mapToDouble(i -> 1.0 / i).sum();
        return Arrays.stream(arr)
                .mapToDouble(i -> s * (1.0 / i) / total)
                .mapToLong(Math::round)
                .toArray();
    }
}
