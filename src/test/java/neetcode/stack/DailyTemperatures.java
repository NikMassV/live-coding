package neetcode.stack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DailyTemperatures {

    @Test
    public void test() {
        assertArrayEquals(new int[]{1, 4, 1, 2, 1, 0, 0}, dailyTemperatures(new int[]{30, 38, 30, 36, 35, 40, 28}));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            int t = temperatures[i];
            while (!stack.isEmpty() && t > stack.peek()[0]) {
                int[] pair = stack.pop();
                res[pair[1]] = i - pair[1];
            }
            stack.push(new int[]{t, i});
        }
        return res;
    }
}
