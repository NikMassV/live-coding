package codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareEveryDigit {

    @Test
    public void test() {
        assertEquals(811181, squareDigits(9119));  // Output: 811181
        assertEquals(493625, squareDigits(765));   // Output: 493625
    }

    public static int squareDigits(int num) {
        StringBuilder result = new StringBuilder();
        while (num != 0) {
            int digit = num % 10;
            result.insert(0, digit * digit);
            num /= 10;
        }
        return result.isEmpty() ? 0 : Integer.parseInt(result.toString());
    }
}
