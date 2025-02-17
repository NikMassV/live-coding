import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsecutiveCharacters {

    @Test
    public void test() {
        assertEquals(2, maxPower("leetcode"));
        assertEquals(5, maxPower("abbcccddddeeeeedcba"));
    }

    public int maxPower(String s) {
        char[] chars = s.toCharArray();
        int maxPower = 1;
        int count = 1;
        int i = 1;
        while (i < chars.length) {
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            i++;
            maxPower = Math.max(count, maxPower);
        }
        return maxPower;
    }
}
