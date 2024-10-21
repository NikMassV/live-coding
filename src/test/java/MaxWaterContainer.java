import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxWaterContainer {

    @Test
    public void test() {
        assertEquals(36, maxArea(new int[]{1, 7, 2, 5, 4, 7, 3, 6}));
        assertEquals(4, maxArea(new int[]{2,2,2}));
    }

    private int maxArea(int[] heights) {
        int l = 0;
        int r = heights.length - 1;
        int result = 0;
        while (l < r) {
            int area = Math.min(heights[l], heights[r]) * (r - l);
            result = Math.max(result, area);
            if (heights[l] <= heights[r]) {
                l++;
            } else {
                r--;
            }
        }
        return result;
    }
}
