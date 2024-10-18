import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringEncodeAndDecode {

    @Test
    public void test() {
        assertEquals("4#neet4#code4#love3#you", encode(List.of("neet", "code", "love", "you")));
        assertEquals(List.of("neet", "code", "love", "you"), decode("4#neet4#code4#love3#you"));
    }

    public String encode(List<String> strs) {
        StringBuilder encodedString = new StringBuilder();
        for (String str : strs) {
            encodedString.append(str.length()).append("#").append(str);
        }
        return encodedString.toString();
    }

    public List<String> decode(String str) {
        List<String> decodedStrings = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int j = i;
            while (str.charAt(j) != '#') j++;
            int length = Integer.valueOf(str.substring(i, j));
            i = j + 1 + length;
            decodedStrings.add(str.substring(j + 1, i));
        }
        return decodedStrings;
    }
}
