package codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSum {

    @Test
    public void test() {
        assertEquals(46, quickSum("ACM"));
        assertEquals(650, quickSum("MID CENTRAL"));
        assertEquals(15, quickSum("BBC"));
        assertEquals(0, quickSum("???"));
        assertEquals(0, quickSum("axg"));
        assertEquals(0, quickSum("234 234 WEF ASDF AAA 554211 ???? "));
        assertEquals(75, quickSum("A C M"));
    }

//    private int quickSum(String packet){
//        int sum = 0;
//        for (int i = 0; i < packet.length(); i++) {
//            char c = packet.charAt(i);
//            if (!Character.isLetter(c) || !Character.isUpperCase(c) && !Character.isSpaceChar(c)) {
//                return 0;
//            }
//            if (Character.isSpaceChar(c)) {
//                sum += 0;
//            } else if (Character.isLetter(c) && Character.isUpperCase(c)) {
//                sum += (i + 1) * (c - 'A' + 1);
//            }
//        }
//        return sum;
//    }

    private int quickSum(String packet) {
        int sum = 0;
        if (packet == null || packet.length() == 0) {
            return sum;
        }
        for (int i = 0; i < packet.length(); i++) {
            char c = packet.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                sum += (i + 1) * (c - 'A' + 1);
            } else if (c != ' ') {
                return 0;
            }
        }
        return sum;
    }
}
