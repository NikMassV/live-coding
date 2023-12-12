package codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmallEnough {

    @Test
    public void test() {
        assertTrue(smallEnough(new int[] { 66, 101 }, 200));
        assertFalse(smallEnough(new int[] { 78, 117, 110, 99, 104, 117, 107, 115 }, 100));
        assertTrue(smallEnough(new int[] { 101, 45, 75, 105, 99, 107 }, 107));
        assertTrue(smallEnough(new int[] { 80, 117, 115, 104, 45, 85, 112, 115 }, 120));
    }

    private boolean smallEnough(int[] a, int limit)
    {
        for(int el : a) {
            if(el > limit) {
                return false;
            }
        }
        return true;
    }
}
