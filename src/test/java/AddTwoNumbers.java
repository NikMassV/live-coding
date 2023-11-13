/*
https://leetcode.com/problems/add-two-numbers/
        Create a dummy node which is the head of the new linked list.
        Create a node temp, initialize it with the dummy.
        Initialize carry to 0.
        Loop through lists l1 and l2 until you reach both ends, and until carry is present.
        Set sum = l1.val + l2.val + carry.
        Update carry = sum / 10.
        Create a new node with the digit value of (sum % 10) and set it to temp node’s next, then advance temp node to next.
        Advance both l1 and l2.
        Return dummy’s next nod
 */

import org.junit.jupiter.api.Test;

public class AddTwoNumbers {

    @Test
    public void test() {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
//        ListNode expected = new ListNode(7, new ListNode(0, new ListNode(8)));
        ListNode result = addTwoNumbers(l1, l2);
        System.out.println(result);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode temp = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry == 1) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            carry = sum / 10;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
        }
        return dummy.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode pointer1 = l1, pointer2 = l2, currentNode = result;
        int dozenCarrier = 0;

        while (pointer1 != null || pointer2 != null) {
            int pointer1Value = (pointer1 == null) ? 0 : pointer1.val;
            int pointer2Value = (pointer2 == null) ? 0 : pointer2.val;

            int sum = pointer1Value + pointer2Value + dozenCarrier;
            dozenCarrier = sum / 10;
            currentNode.next = new ListNode (sum % 10);
            currentNode = currentNode.next;

            if(pointer1 != null) {
                pointer1 = pointer1.next;
            }

            if(pointer2 != null) {
                pointer2 = pointer2.next;
            }
        }

        if(dozenCarrier > 0) {
            currentNode.next = new ListNode(dozenCarrier);
        }
        return result.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
