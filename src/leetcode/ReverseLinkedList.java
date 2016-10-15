package leetcode;
import java.util.*;
import java.util.stream.*;

class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

public class ReverseLinkedList {
	
	public static void main(String[] args) {
	    ListNode head = new ListNode(1);
	    ListNode  node= new ListNode(2);
		head.next = node;

		ListNode res = new ReverseLinkedList().reverse(head);
		System.out.println(res.val);
	}
    public ListNode reverse(ListNode head) {
        if (head == null)    
            return null;

        return aux(null, head);        
    }

    public ListNode reverseInPlace(ListNode head) {
        if (head == null)
            return null;

        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode next = cur.next; 
            cur.next = prev;
            prev = cur;
            cur = next; 
        }

        return prev;
    }

    private ListNode aux(ListNode prev, ListNode cur) {
        if (cur == null)
            return prev;

        ListNode head = aux(cur, cur.next);
        cur.next = prev;
        return head;
    }
}
