package leetcode;

public class ReverseLinkedList {
	
	public static void main(String[] args) {
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

    private Node aux(Node prev, Node cur) {
        if (cur == null)
            return prev;

        Node head = aux(cur, cur.next);
        cur.next = prev;
        return head;
    }
}
