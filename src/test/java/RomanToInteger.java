import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanToInteger {

    @Test
    public void test() {
        assertEquals(3, romanToInt("III"));
        assertEquals(4, romanToInt("IV"));
        assertEquals(58, romanToInt("LVIII"));
        assertEquals(1994, romanToInt("MCMXCIV"));
        assertEquals(3724, romanToInt("MMMDCCXXIV"));
    }

    private int romanToInt(String s) {
        var romanNumerals = Map.ofEntries(
                Map.entry("I", 1),
                Map.entry("IV", 4),
                Map.entry("V", 5),
                Map.entry("IX", 9),
                Map.entry("X", 10),
                Map.entry("XL", 40),
                Map.entry("L", 50),
                Map.entry("XC", 90),
                Map.entry("C", 100),
                Map.entry("CD", 400),
                Map.entry("D", 500),
                Map.entry("CM", 900),
                Map.entry("M", 1000)
        );
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            String currentSymbol = "" + s.charAt(i);
            String nextSymbol = (i + 1 < s.length()) ? "" + s.charAt(i + 1) : "";
            String combinedSymbol = currentSymbol + nextSymbol;
            if (romanNumerals.containsKey(combinedSymbol)) {
                ans += romanNumerals.get(combinedSymbol);
                i++;
            } else {
                ans += romanNumerals.get(currentSymbol);
            }
        }
        return ans;
    }
}
