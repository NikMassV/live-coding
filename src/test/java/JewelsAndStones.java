import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JewelsAndStones {

    @Test
    public void test() {
        assertEquals(3, numJewelsInStones("aA", "aAAbbbb"));
        assertEquals(0, numJewelsInStones("z", "ZZ"));
    }

    private int numJewelsInStones(String jewels, String stones) {
        int result = 0;
        Set<Character> charSet = new HashSet<>();
        for (Character j : jewels.toCharArray()) {
            charSet.add(j);
        }
        for (Character s : stones.toCharArray()) {
            if (charSet.contains(s)) {
                result++;
            }
        }
        return result;
    }
}
