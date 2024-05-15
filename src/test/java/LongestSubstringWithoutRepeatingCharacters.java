import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestSubstringWithoutRepeatingCharacters {

    @Test
    public void test() {
        assertEquals(lengthOfLongestSubstring("abcabcbb"), 3);
        assertEquals(lengthOfLongestSubstring("bbbbb"), 1);
        assertEquals(lengthOfLongestSubstring("pwwkew"), 3);
    }

    private int lengthOfLongestSubstring(String s) {
        int startIndex = 0;
        int endIndex = 0;
        var charSet = new HashSet<>();
        int ans = 0;
        while (endIndex < s.length()) {
            if (charSet.contains(s.charAt(endIndex))) {
                while(charSet.contains(s.charAt(endIndex))) {
                    charSet.remove(s.charAt(startIndex));
                    startIndex++;
                }
            } else {
                charSet.add(s.charAt(endIndex));
                endIndex++;
            }
            ans = Math.max(endIndex - startIndex, ans);
        }
        return ans;
    }
}
