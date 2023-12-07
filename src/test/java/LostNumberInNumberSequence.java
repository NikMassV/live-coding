import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LostNumberInNumberSequence {

    @Test
    public void test() {
        assertEquals(0, findDeletedNumber(new int[]{1, 2, 3}, new int[]{2, 1, 3}));
    }

    public static int findDeletedNumber(int[] arr, int[] mixedArr) {
        Arrays.sort(mixedArr);
//       System.out.println("basic: " + Arrays.toString(arr));
//       System.out.println("sorted: " + Arrays.toString(mixedArr));
        for (int i = 0; i < arr.length; i++) {
            if (i == mixedArr.length || arr[i] != mixedArr[i]) {
                return arr[i];
            }
        }
        return 0;
    }
}
