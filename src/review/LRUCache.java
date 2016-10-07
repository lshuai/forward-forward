package review;
import java.util.*;

class Node {
    public int key;
    public int val;
    public Node next;
    public Node prev;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

public class LRUCache {
    public static void main(String[] args) throws Exception {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(2));
    }
    
    private int capacity;

    private Node head;

    private Node tail;

    private Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<Integer, Node>();
    }

    public int get(int key) throws Exception {
        if (!this.map.containsKey(key))
            throw new Exception("no such key: " + key);
        
        Node hit = this.map.get(key);
        int val = hit.val;
        this.swapWithHead(hit);
        return val;
    }

    private void swapWithHead(Node n) {
        int tmp = n.key;
        n.key = this.head.key;
        this.head.key = tmp;
        tmp = n.val;
        n.val = this.head.val;
        this.head.val = n.val;
        this.map.put(this.head.key, this.head);
        this.map.put(n.key, n);
    }

    public boolean put(int key, int val) {
        if (this.map.containsKey(key)) {
            this.map.get(key).val = val;
            return true;
        }
        if (this.map.size() == this.capacity) {
            this.map.remove(this.tail.key);
            if (this.tail == this.head) {
                this.tail = null;
                this.head = null;
            }
            else {
                this.tail = this.tail.prev;
                this.tail.next = null;
            }
        }
        this.setHead(key, val);
        return true;
    }

    private void setHead(int key, int val) {
        if (this.head == null) {
            this.head = new Node(key, val);
            this.tail = this.head;
            this.map.put(key, this.head);
        }
        else {
            Node n = new Node(key, val);
            this.map.put(key, n);
            n.next = this.head;            
            this.head.prev = n;
            this.head = n;
        }
    }
}
