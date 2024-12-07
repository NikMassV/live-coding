package neetcode.heappriorityqueue;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KthLargestElementInAnArray {

    @Test
    public void test() {
        assertEquals(4, findKthLargest(new int[]{2, 3, 1, 5, 4}, 2));
        assertEquals(4, findKthLargest(new int[]{2, 3, 1, 1, 5, 5, 4}, 3));
    }

    private int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
}
