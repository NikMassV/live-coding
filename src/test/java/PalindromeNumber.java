import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromeNumber {

    @Test
    public void test() {
        assertTrue(isPalindrome(121));
        assertFalse(isPalindrome(1321));
    }

    private boolean isPalindrome(int x) {
        if(x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }
        int invertedX = 0;
        int xCopy = x;
        while(xCopy != 0) {
            invertedX = invertedX * 10 + xCopy % 10;
            xCopy = xCopy / 10;
        }
        return invertedX == x;
    }
}
