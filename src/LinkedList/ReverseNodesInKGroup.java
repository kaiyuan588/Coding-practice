package LinkedList;
// You are given the head of a singly linked list head and a positive integer k.

// You must reverse the first k nodes in the linked list, and then reverse the next k nodes, and so on. If there are fewer than k nodes left, leave the nodes as they are.

// Return the modified list after reversing the nodes in each group of k.

// You are only allowed to modify the nodes' next pointers, not the values of the nodes.

// Example 1:
// Input: head = [1,2,3,4,5,6], k = 3

// Output: [3,2,1,6,5,4]

// Input: head = [1,2,3,4,5], k = 3

// Output: [3,2,1,4,5]
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            ListNode kth = findKth(groupPrev, k);
            if (kth == null) break;
            ListNode groupNext = kth.next;

            ListNode cur = groupPrev.next;
            ListNode prev = groupNext;

            while (cur != groupNext) {
                ListNode nextNode = cur.next;
                cur.next = prev;
                prev = cur;
                cur = nextNode;
            }

            // reconnect
            ListNode oldGroupHead = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = oldGroupHead;
        }

        return dummy.next;
    }
    public ListNode findKth(ListNode start, int k) {
        while (start != null && k != 0) {
            start = start.next;
            k--;
        }
        return start;
    }
}
