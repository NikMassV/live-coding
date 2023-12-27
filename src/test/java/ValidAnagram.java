import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidAnagram {

    @Test
    public void test() {
        assertTrue(isAnagram("anagram", "nagaram"));
        assertFalse(isAnagram("rat", "car"));
        assertTrue(isAnagram1("anagram", "nagaram"));
        assertFalse(isAnagram1("rat", "car"));
    }

    private boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        return Arrays.equals(sArray, tArray);
    }


    private boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Long> sStringCounterMap = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        Map<Character, Long> tStringCounterMap = t.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        return sStringCounterMap.equals(tStringCounterMap);
    }

}
