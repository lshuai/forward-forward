import java.util.*;
import java.util.stream.*;

class ListNode {
  int val;
  ListNode next;
  ListNode(int x) { val = x; }
}

public class MergeKSortedList {
    public static void main(String[] args) {

    }

    public ListNode merge(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<ListNode>(100, (a, b) -> a.val < b.val ? -1 : 1);
        for (ListNode head: lists) {
            if (head != null) 
                priorityQueue.offer(head);
        }
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (!priorityQueue.isEmpty()) {
            ListNode min = priorityQueue.poll();
            if (min.next != null)
                priorityQueue.offer(min.next);
            cur.next = min;
            cur = cur.next;
        }

        return head.next;
    }
}
