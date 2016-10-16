package leetcode;
import java.util.*;

public class RandomizedCollection {
    
    public static void main(String[] args) {
        RandomizedCollection rc = new RandomizedCollection();
        System.out.println(rc.insert(0));
        System.out.println(rc.insert(1));
        System.out.println(rc.insert(2));
        System.out.println(rc.insert(3));
        System.out.println(rc.insert(3));
        System.out.println(rc.remove(2));
        System.out.println(rc.remove(3));
        System.out.println(rc.remove(0));
    }

    private List<Integer> vals;

    private Map<Integer, Set<Integer>> valToIndex;

    private Random rand;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        this.vals = new ArrayList<Integer>();
        this.valToIndex = new HashMap<Integer, Set<Integer>>();
        this.rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        this.vals.add(val);        
        if (this.valToIndex.containsKey(val)) {
            this.valToIndex.get(val).add(this.vals.size() - 1);
            return false;
        }
        else {
            this.valToIndex.put(val, new HashSet<Integer>(Arrays.asList(this.vals.size() - 1)));
            return true;
        }
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!this.valToIndex.containsKey(val))    
            return false;
        if (!this.valToIndex.get(val).contains(this.vals.size() - 1)) {
            int iToDel = this.valToIndex.get(val).iterator().next();
            int lastVal = this.vals.get(this.vals.size() - 1);
            this.valToIndex.get(lastVal).remove(this.vals.size() - 1);
            this.valToIndex.get(lastVal).add(iToDel);
            this.valToIndex.get(val).remove(iToDel);
            this.valToIndex.get(val).add(this.vals.size() - 1);
            this.vals.set(iToDel, lastVal);
        }
        this.valToIndex.get(val).remove(this.vals.size() - 1); 
        if (this.valToIndex.get(val).isEmpty())
            this.valToIndex.remove(val);
        this.vals.remove(this.vals.size() - 1);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return this.vals.get(this.rand.nextInt(this.vals.size()));
    }
}
