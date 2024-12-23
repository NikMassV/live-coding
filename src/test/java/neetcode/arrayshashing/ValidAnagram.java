package neetcode.arrayshashing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidAnagram {

    @Test
    public void testSortArray() {
        assertTrue(isAnagramSortArray("anagram", "nagaram"));
        assertFalse(isAnagramSortArray("rat", "car"));
    }

    @Test
    public void testMapCounter() {
        assertTrue(isAnagramMapCounter("anagram", "nagaram"));
        assertFalse(isAnagramMapCounter("rat", "car"));
    }

    @Test
    public void test1() {
        assertTrue(isAnagramArrayCoderCounter("anagram", "nagaram"));
        assertFalse(isAnagramArrayCoderCounter("rat", "car"));
    }

    private boolean isAnagramSortArray(String s, String t) { //O(nlogn+mlogm)
        if (s.length() != t.length()) {
            return false;
        }
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        return Arrays.equals(sArray, tArray);
    }

    private boolean isAnagramMapCounter(String s, String t) {
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

    private boolean isAnagramArrayCoderCounter(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] store = new int[26];
        for (int i = 0; i < s.length(); i++) {
            store[s.charAt(i) - 'a']++;
            store[t.charAt(i) - 'a']--;
        }
        for (int n : store) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }
}
