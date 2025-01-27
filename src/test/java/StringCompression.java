import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCompression {

    @Test
    public void test() {
        assertEquals(6, compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
        assertEquals(1, compress(new char[]{'a'}));
        assertEquals(4, compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
    }

    private int compress(char[] chars) {
        int result = 0;
        int length = chars.length;
        int i = 0;
        char letter;
        int count;
        while (i < length) {
            letter = chars[i];
            count = 0;
            while (i < length && chars[i] == letter) {
                count += 1;
                i += 1;
            }
            chars[result] = letter;
            result += 1;
            if (count > 1) {
                char[] charCount = Integer.toString(count).toCharArray();
                System.arraycopy(charCount, 0, chars, result, charCount.length);
                result += charCount.length;
            }
        }
        return result;
    }
}
