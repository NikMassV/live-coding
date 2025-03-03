import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTheIndexOfTheFirstOccurrenceInAString {

    @Test
    public void test() {
        assertEquals(0, strStr("sadbutsad", "sad"));
        assertEquals(-1, strStr("leetcode", "leeto"));
    }

    private int strStr(String haystack, String needle) {
        int haystackLength = haystack.length();
        int needleLength = needle.length();
        if (needleLength > haystackLength) return -1;
        for (int i = 0; i <= haystackLength - needleLength; i++) {
            if (haystack.substring(i, i + needleLength).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
