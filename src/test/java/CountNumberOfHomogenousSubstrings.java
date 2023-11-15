//https://leetcode.com/problems/count-number-of-homogenous-substrings/

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountNumberOfHomogenousSubstrings {

    @Test
    public void test() {
        assertEquals(13, countHomogenous("abbcccaa"));
        assertEquals(2, countHomogenous("xy"));
        assertEquals(15, countHomogenous("zzzzz"));
    }

    public int countHomogenous(String s) {
        int n = s.length();
        int count = 0;
        int mod = 1000000007;
        for (int i = 0; i < n; ) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            int len = j - i;
            count = (int) ((count + (long) len * (len + 1) / 2) % mod);
            i = j;
        }
        return count;
    }
}
