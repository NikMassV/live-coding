package neetcode.heappriorityqueue;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class KClosestPointsToOrigin {

    @Test
    public void test() {
        assertArrayEquals(new int[][]{{0, 2}, {2, 0}}, kClosest(new int[][]{{0, 2}, {2, 0}, {2, 2}}, 2));
    }

    private int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(b[0] * b[0] + b[1] * b[1],
                        a[0] * a[0] + a[1] * a[1])
        );
        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        int[][] res = new int[k][2];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            res[i++] = maxHeap.poll();
        }
        return res;
    }
}
