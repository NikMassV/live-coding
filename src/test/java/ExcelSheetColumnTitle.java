//https://leetcode.com/problems/excel-sheet-column-title/

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExcelSheetColumnTitle {

    @Test
    public void test() {
        assertEquals("ABV", solution(750));
    }

    public String solution(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            int remainder = (columnNumber - 1) % 26;
            char letter = (char) ('A' + remainder);
            sb.insert(0, letter);
            columnNumber = (columnNumber - 1) / 26;
        }
        return sb.toString();
    }
}
