import org.junit.jupiter.api.Test;

public class ExcelSheetColumnTitle {

    @Test
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
