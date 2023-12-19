package codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaxativeShotRoulette {

    @Test
    public void test() {
        assertEquals(0.5, getChance(2, 1, 1));
        assertEquals(0.25, getChance(4, 1, 3));
        assertEquals(0.33, getChance(100, 10, 10));
        assertEquals(0.04, getChance(1000, 150, 20));
        assertEquals(0.29, getChance(25, 5, 5));
        assertEquals(0.42, getChance(9, 3, 2));
    }

    private double getChance(int n, int x, int a) {
        double result = 1;
        for (int i = 0; i < a; i++) {
            result *= (double) (n - x - i) / (n - i);
        }
        return (double) Math.round(result * 100) / 100;
    }
}
