package codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmallestValueOfAnArray {

    @Test
    public void test() {
        assertEquals(0, findSmallest(new int[]{1, 2, 3}, "index"));
        assertEquals(2, findSmallest(new int[]{7, 12, 3, 2, 27}, "value"));
        assertEquals(3, findSmallest(new int[]{7, 12, 3, 2, 27}, "index"));
    }

    public int findSmallest(final int[] numbers, final String toReturn) {
        int min = numbers[0];
        int minIndex = 0;
        for (int i = 1; i < numbers.length; i++) {
            if (min > numbers[i]) {
                min = numbers[i];
                minIndex = i;
            }
        }
        return toReturn.equals("value") ? min : minIndex;
    }
}
