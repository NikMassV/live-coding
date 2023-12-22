import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class GroupAnagrams {

    @Test
    public void test() {
        List<List<String>> expected = List.of(List.of("bat"), List.of("tan", "nat"), List.of("eat", "tea", "ate"));
        assertThat(expected).hasSameElementsAs(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    private List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> keyAnagramGroupMap = new HashMap<>();
        for (String s : strs) {
            char[] sCharArray = s.toCharArray();
            Arrays.sort(sCharArray);
            String key = String.valueOf(sCharArray);
            if (!keyAnagramGroupMap.containsKey(key)) {
                keyAnagramGroupMap.put(key, new ArrayList<>());
            }
            keyAnagramGroupMap.get(key).add(s);
        }
        return new ArrayList<>(keyAnagramGroupMap.values());
    }
}
