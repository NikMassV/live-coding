import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromeString {

    @Test
    public void testTrue() {
        assertTrue(isPalindrome("1Red rum, sir, is murder1"));
    }

    @Test
    public void testFalse() {
        assertFalse(isPalindrome("apple"));
    }

    private boolean isPalindrome(String str) {
        var simplified = simplifyString(str);
        var reversedSimplified = new StringBuilder(simplified).reverse().toString();
        return simplified.equals(reversedSimplified);
    }

    private String simplifyString(String str) {
        return str.toLowerCase().replaceAll("[^a-z0-9]", "");
    }
}
