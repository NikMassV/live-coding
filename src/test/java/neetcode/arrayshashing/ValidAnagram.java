package neetcode.arrayshashing;

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
        assertTrue(isAnagram2("anagram", "nagaram"));
        assertFalse(isAnagram2("rat", "car"));
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

    private boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] store = new int[26];
        for (int i = 0; i < s.length(); i++) {
            store[s.charAt(i) - 'a']++;
            store[t.charAt(i) - 'a']--;
        }
        for (int n : store) if (n != 0) return false;
        return true;
    }
}
