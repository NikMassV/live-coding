import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidPalindrome {

    @Test
    public void test() {
        assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(isPalindrome("race a car"));
        assertTrue(isPalindrome(" "));
    }

    private boolean isPalindrome(String s) {
        if(s == null) {
            throw new IllegalArgumentException("Input string cna not be null.");
        }
        if(s.isEmpty()) {
            return true;
        }
        s = s.replaceAll("[^A-Za-z0-9]", "");
        s = s.toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
