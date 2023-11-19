import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class PlusOne {

    @Test
    public void test() {
        assertArrayEquals(new int[]{1,0,0,0}, plusOne(new int[] {9,9,9}));
        assertArrayEquals(new int[]{2,0,0}, plusOne(new int[] {1,9,9}));
        assertArrayEquals(new int[]{9,2,0}, plusOne(new int[] {9,1,9}));
    }

    private int[] plusOne(int[] digits) {
        int index = digits.length - 1;

        while(digits[index] == 9) {
            digits[index] = 0;
            index--;

            if(index < 0) {
                digits = new int[digits.length + 1];
                digits[0] = 1;
                break;
            }
        }

        if(index >= 0) {
            digits[index]++;
        }
        return digits;
    }
}
