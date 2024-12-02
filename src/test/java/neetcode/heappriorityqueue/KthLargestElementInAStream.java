package neetcode.heappriorityqueue;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KthLargestElementInAStream {

    @Test
    public void test() {
        KthLargest kthLargest = new KthLargest(3, new int[]{1, 2, 3, 3});
        assertEquals(3, kthLargest.add(3));
        assertEquals(3, kthLargest.add(5));
        assertEquals(3, kthLargest.add(6));
        assertEquals(5, kthLargest.add(7));
        assertEquals(6, kthLargest.add(8));
    }

    private class KthLargest {

        private PriorityQueue<Integer> minHeap;
        private int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.minHeap = new PriorityQueue<>();
            for (int num : nums) {
                minHeap.offer(num);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
        }

        public int add(int val) {
            minHeap.offer(val);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
            return minHeap.peek();
        }
    }
}
