//https://leetcode.com/problems/reverse-integer/description/

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseInteger {

    @Test
    public void test() {
        assertEquals(321, reverse(123));
    }

    private int reverse(int x) {
        int result = 0;
        if (x > 2147483647) {
            return 0;
        } else {
            while(x != 0) {
                int pop = x % 10;
                x = x / 10;
                if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && pop > 7)) {
                    return 0;
                }
                if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && pop < -8)) {
                    return 0;
                }
                result = result * 10 + pop;
            }
            return result;
        }
    }
}
