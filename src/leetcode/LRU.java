package leetcode;
import java.util.*;
import java.util.stream.*;

class Node {
    public String key;
    public String val;
    public Node next;
    public Node prev;
    
    public Node(String val) {
    	this.val = val;
    }

    public Node(String key, String val) {
        this.key = key;
        this.val = val;
    }
}

public class LRU {
    private final Map<String, Node> map;

    private Node head;

    private Node tail;

    private final int size;

    public LRU(int size) {
        this.head = null;
        this.tail = null;
        this.map = new HashMap<String, Node>();
        this.size = size <= 0 ? 1 : size;
    }

    public String get(String key) {
        if (key == null)
            return null;
        if (!this.map.containsKey(key))
            return null;

        Node hit = this.map.get(key); 
        String val = hit.val;
        swapHead(hit);
        return val;
    }

    public boolean set(String key, String val) {
        if (key == null || val == null)
            return false;
        if (this.map.containsKey(key)) {
            this.map.get(key).val = val;
            swapHead(this.map.get(key)); 
        }
        else {
            Node n = new Node(key, val);
            if (this.map.size() >= size) {
                this.map.remove(this.tail.key);
                if (this.tail.prev != null)
                    this.tail.prev.next = null;
                if (this.head == this.tail)
                    this.head = null;
                this.tail = this.tail.prev;
            }
            this.map.put(key, n);
            this.setHead(n); 
        }
        return true;
    }

    private void setHead(Node n) {
        if (this.head == null) {
            this.head = this.tail = n;
            return;
        }
        n.next = this.head;
        this.head.prev = n;
        this.head = n;
    }

    private void swapHead(Node n) {
        if (n == this.head) 
            return;

        this.map.put(n.key, this.head);
        this.map.put(this.head.key, n);
        swap(this.head, n);
    }

    private static void swap(Node a, Node b) {
        String tmp = a.key;
        a.key = b.key;
        b.key = tmp;
        tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }
}
