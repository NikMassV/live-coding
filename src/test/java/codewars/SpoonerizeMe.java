package codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpoonerizeMe {

    @Test
    public void test() {
        assertEquals("pit of the nicking", spoonerize("nit of the picking"));
        assertEquals("bedding wells", spoonerize("wedding bells"));
        assertEquals("belly jeans", spoonerize("jelly beans"));
    }

    public String spoonerize(String words) {
        String[] splited = words.split(" ");
        StringBuilder result = new StringBuilder();
        String first_word = splited[0];
        String last_word = splited[splited.length - 1];
        String first_word1 = last_word.charAt(0) + first_word.substring(1);
        String last_word1 = first_word.charAt(0) + last_word.substring(1);
        splited[0] = first_word1;
        splited[splited.length - 1] = last_word1;
        for (String word : splited) {
            result.append(word).append(" ");
        }
        return result.toString().trim();
    }
}
