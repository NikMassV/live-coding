package codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HowFarWillIGO {

    @Test
    public void test() {
        assertEquals(1120, travel(1000, 10, 127, 14));
        assertEquals(1000, travel(100, 10, 0, 10));
        assertEquals(1000, travel(100, 10, 0, 10));
        assertEquals(450, travel(25, 50, 120, 18));
    }

    private int travel(int totalTime, int runTime, int restTime, int speed) {
        if (totalTime <= runTime) {
            return totalTime * speed;
        }
        int cicles = totalTime / (runTime + restTime);
        int remainingTime = totalTime % (runTime + restTime);
        if (remainingTime >= runTime) {
            return (cicles + 1) * runTime * speed;
        } else {
            return remainingTime * speed + cicles * runTime * speed;
        }
    }
}
