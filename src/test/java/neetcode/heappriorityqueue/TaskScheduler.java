package neetcode.heappriorityqueue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskScheduler {

    @Test
    public void test() {
        assertEquals(5, leastInterval(new char[]{'X', 'X', 'Y', 'Y'}, 2));
        assertEquals(9, leastInterval(new char[]{'A', 'A', 'A', 'B', 'C'}, 3));
    }

    private int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }
        int maxf = Arrays.stream(count).max().getAsInt();
        int maxCount = 0;
        for (int i : count) {
            if (i == maxf) {
                maxCount++;
            }
        }
        int time = (maxf - 1) * (n + 1) + maxCount;
        return Math.max(tasks.length, time);
    }
}
