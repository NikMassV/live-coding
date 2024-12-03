package neetcode.heappriorityqueue;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LastStoneWeight {

    @Test
    public void test() {
        assertEquals(1, lastStoneWeight(new int[]{2, 3, 6, 2, 4}));
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int s : stones) {
            minHeap.offer(-s);
        }
        while (minHeap.size() > 1) {
            int first = minHeap.poll();
            int second = minHeap.poll();
            if (second > first) {
                minHeap.offer(first - second);
            }
        }
        minHeap.offer(0);
        return Math.abs(minHeap.peek());
    }
}
