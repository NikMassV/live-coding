package neetcode.binarysearch;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeBasedKeyValueStore {

    @Test
    public void test() {
        TimeMap timeMap = new TimeMap();
        timeMap.set("alice", "happy", 1);
        assertEquals("happy", timeMap.get("alice", 1));
        assertEquals("happy", timeMap.get("alice", 2));
        timeMap.set("alice", "sad", 3);
        assertEquals("sad", timeMap.get("alice", 3));
    }

    private class TimeMap {
        private Map<String, TreeMap<Integer, String>> m;

        public TimeMap() {
            m = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            m.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!m.containsKey(key)) return "";
            TreeMap<Integer, String> timestamps = m.get(key);
            Map.Entry<Integer, String> entry = timestamps.floorEntry(timestamp);
            return entry == null ? "" : entry.getValue();
        }
    }
}
