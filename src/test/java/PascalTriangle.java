import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PascalTriangle {

    @Test
    public void test() {
        assertEquals(List.of(List.of(1),List.of(1,1),List.of(1,2,1),List.of(1,3,3,1),List.of(1,4,6,4,1)),
                generate(5));
        assertEquals(List.of(List.of(1)), generate(1));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows == 0) {
            return triangle;
        }
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);
        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 1; j < i; j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            row.add(1);
            triangle.add(row);
        }
        return triangle;
    }
}
