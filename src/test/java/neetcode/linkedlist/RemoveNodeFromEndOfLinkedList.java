package neetcode.linkedlist;

import org.example.domain.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveNodeFromEndOfLinkedList {

    @Test
    public void test() {
        ListNode actual = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode expected = new ListNode(1, new ListNode(2, new ListNode(4)));
        assertEquals(expected, removeNthFromEnd(actual, 2));
    }

    private ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode left = dummy;
        ListNode right = head;
        while (n > 0) {
            right = right.next;
            n--;
        }
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return dummy.next;
    }
}
