package neetcode.linkedlist;

import org.example.domain.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReorderLinkedList {

    @Test
    public void test() {
        ListNode actual = new ListNode(2, new ListNode(4, new ListNode(6, new ListNode(8, new ListNode(10)))));
        ListNode expected = new ListNode(2, new ListNode(10, new ListNode(4, new ListNode(8, new ListNode(6)))));
        reorderList(actual);
        assertEquals(expected, actual);
    }

    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = slow.next;
        ListNode prev = slow.next = null;
        while (second != null) {
            ListNode tmp = second.next;
            second.next = prev;
            prev = second;
            second = tmp;
        }
        ListNode first = head;
        second = prev;
        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;
            first.next = second;
            second.next = tmp1;
            first = tmp1;
            second = tmp2;
        }
    }
}
