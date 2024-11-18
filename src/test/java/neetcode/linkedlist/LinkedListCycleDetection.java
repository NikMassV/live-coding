package neetcode.linkedlist;

import org.example.domain.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class LinkedListCycleDetection {

    @Test
    public void test() {
        assertFalse(hasCycle(new ListNode(1, new ListNode(2))));
    }

    private boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
