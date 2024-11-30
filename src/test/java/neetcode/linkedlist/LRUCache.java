package neetcode.linkedlist;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LRUCache {

    @Test
    public void test() {
        LRUC lRUCache = new LRUC(2);
        lRUCache.put(1, 10);
        assertEquals(10, lRUCache.get(1));
        lRUCache.put(2, 20);
        lRUCache.put(3, 30);
        assertEquals(20, lRUCache.get(2));
        assertEquals(-1, lRUCache.get(1));
    }

    private class LRUC {
        private final Map<Integer, Integer> cache;
        private final int capacity;

        public LRUC(int capacity) {
            this.capacity = capacity;
            this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > LRUC.this.capacity;
                }
            };
        }

        public int get(int key) {
            return cache.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            cache.put(key, value);
        }
    }
}
