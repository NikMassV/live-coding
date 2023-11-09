//https://leetcode.com/problems/count-number-of-homogenous-substrings/

import org.junit.jupiter.api.Test;

public class CountNumberOfHomogenousSubstrings {

    @Test
    public void test() {
        String s0 = "bab";
        int count0 = countHomogenous(s0);
        System.out.println(count0);
        String s1 = "abbcccaa";
        int count1 = countHomogenous(s1);
        System.out.println(count1); // Output: 13
        String s2 = "xy";
        int count2 = countHomogenous(s2);
        System.out.println(count2); // Output: 2
        String s3 = "zzzzz";
        int count3 = countHomogenous(s3);
        System.out.println(count3); // Output: 15
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
