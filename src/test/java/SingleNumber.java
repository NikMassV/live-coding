import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleNumber {

    @Test
    public void test() {
        assertEquals(1, singleNumber(new int[] {2,2,1}));
        assertEquals(4, singleNumber(new int[] {4,1,2,1,2}));
    }

    private int singleNumber(int[] nums) {
        return Arrays.stream(nums)
                .reduce(0, (x, y) -> x ^ y);
    }
}
