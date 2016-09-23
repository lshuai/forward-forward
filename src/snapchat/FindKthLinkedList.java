import java.util.*;

class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }
}
 
public class FindKthLinkedList {
    public static void main(String[] args) {
        ListNode h = new ListNode(1); 
        h.next = new ListNode(2);
        h.next.next = new ListNode(3);
        h.next.next.next = new ListNode(4);

        System.out.println(findNthFromEnd(h, 3));
    }

    public static int findNthFromEnd(ListNode head, int k) {
        if (head == null)
            return -1;
       
        int[] res = new int[] {-1};
        search(head, k, res); 
        return res[0];
    }

    private static int search(ListNode cur, int k, int[] res) {
        if (cur == null)
            return -1;

        int i = search(cur.next, k, res) + 1;
        if (i == k)
            res[0] = cur.val;
        return i;
    }
}
