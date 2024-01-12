package codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersistentBugger {

    @Test
    public void test() {
        assertEquals(3, persistence(39), "Incorrect answer for n=39");
        assertEquals(0, persistence(4), "Incorrect answer for n=4");
        assertEquals(2, persistence(25), "Incorrect answer for n=25");
        assertEquals(4, persistence(999), "Incorrect answer for n=999");
    }

    private int persistence(long n) {
        if (n < 10) return 0;
        return 1 + persistence(productOfDigits(n));
    }

    private long productOfDigits(long num) {
        long product = 1;
        while (num != 0) {
            product *= (num % 10);
            num /= 10;
        }
        return product;
    }
}
