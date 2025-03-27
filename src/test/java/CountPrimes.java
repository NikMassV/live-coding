import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountPrimes {

    @Test
    public void test() {
        assertEquals(4, countPrimes(10));
        assertEquals(25, countPrimes(100));
        assertEquals(0, countPrimes(0));
        assertEquals(0, countPrimes(1));
    }

    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
                for (long j = (long) i * i; j < n; j += i) {
                    isPrime[(int) j] = false;
                }
            }
        }
        return count;
    }
}
