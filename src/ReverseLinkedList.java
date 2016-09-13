package leetcode
import java.util.*;
import java.util.stream.*;

class Node {
    public int val;
    public Node next;

    public(int val) {
        this.val = val;
    }
}

public class ReverseLinkedList {
	
	public static void main(String[] args) {
		Node head = new Node(1);
		Node  node= new Node(2);
		head.next = node;

		Node res = reverse(head);
		System.out.println(res.val);
	}
    public Node reserse(Node head) {
        if (head == null)    
            return null;

        return aux(null, head);        
    }

    public Node reverseInPlace(Node head) {
        if (head == null)
            return null;

        Node cur = head;
        Node prev = null;
        while (cur != null) {
            Node next = cur.next; 
            cur.next = prev;
            prev = cur;
            cur = next; 
        }

        return prev;
    }

    private void aux(Node prev, Node cur) {
        if (cur == null)
            return prev;

        Node head = aux(cur, cur.next);
        cur.next = prev;
        return head;
    }
}
