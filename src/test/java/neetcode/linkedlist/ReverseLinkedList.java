package neetcode.linkedlist;

import org.example.domain.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseLinkedList {

    @Test
    public void test() {
        ListNode actual = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode expected = new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1))));
        assertEquals(expected, reverse(actual));
    }

    private ListNode reverse(ListNode head) {
        ListNode previousNode = null;
        ListNode currentNode = head;
        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return previousNode;
    }
}
