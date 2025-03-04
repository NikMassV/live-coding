import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LengthOfLastWord {

    @Test
    public void test() {
        assertEquals(5, lengthOfLastWord("Hello World"));
        assertEquals(4, lengthOfLastWord("   fly me   to   the moon  "));
        assertEquals(6, lengthOfLastWord("luffy is still joyboy"));
    }

    public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        String result = "";
        for (String word : words) {
            result = word.trim();
        }
        return result.length();
    }
}
