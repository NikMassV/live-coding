package codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromAtoZ {

    @Test
    public void test() {
        assertEquals("abcdefghijklmnopqrstuvwxyz", gimmeTheLetters("a-z"));
        assertEquals("QRSTUVWXYZ", gimmeTheLetters("Q-Z"));
        assertEquals("a", gimmeTheLetters("a-a"));
    }

    private String gimmeTheLetters(String s) {
        char start = s.charAt(0);
        char end = s.charAt(2);
        StringBuilder result = new StringBuilder();

        for (char i = start; i <= end; i++) {
            result.append(i);
        }

        return result.toString();
    }
}
