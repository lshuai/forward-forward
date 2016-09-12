import java.util.*;
import java.util.stream.*;

class Node {
    public String key;
    public String val;
    public Node next;
    public Node prev;

    public Node(String val, String key) {
        this.val = val;
        this.key = key;
    }
}

public class LRU {
    private final Map<string, Node> map;

    private Node head;

    private Node tail;

    private final int size;

    public LRU(int size) {
        this.map = new HashMap<String, Node>();
        this.head = null;
        this.tail = null;
        this.size = size;
    }

    public String get(String key) {
        if (key == null || key.length() == 0)
            return null;
        if (!this.map.containsKey(key))
            return null;

        Node hit = this.map.get(key);
        String val = hit.val;
        swap(this.head, hit); 
        return val;
    }

    public bool put(String key, String val) {
        if (this.map.containsKey(key)) 
            return false;
        Node newNode = new Node(val, key);
        this.map.put(key, newNode);
        setHead(newNode);
        // need some change
        if (this.map.size() < size) {
            if (this.tail == null)
                this.tail = newNode;
        }
        else {
            this.map.remove(this.tail.key);
            swap(this.tail, this.tail.prev);
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
    }

    private void setHead(Node n) {
        if (head == null) {
            this.head = n;
            return;
        }
        if (n == this.head)
            return;
        n.next = this.head;
        this.head.prev = n;
        this.head = n;
    }

    private static void swap(Node a, Node b) {
        if (a == b)
            return;
        String tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }
}
