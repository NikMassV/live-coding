import org.example.domain.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveDuplicatesFromSortedList {

    @Test
    public void test() {
        ListNode l1 = new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(7))));
        ListNode l2 = new ListNode(7, new ListNode(3, new ListNode(3, new ListNode(3))));
        assertEquals(new ListNode(2, new ListNode(3, new ListNode(7))), deleteDuplicates(l1));
        assertEquals(new ListNode(7, new ListNode(3)), deleteDuplicates(l2));
    }

    private ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while(current != null && current.next != null) {
            if(current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}
