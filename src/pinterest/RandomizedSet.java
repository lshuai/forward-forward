package pinterest;
import java.util.*;
import java.util.stream.*;
import java.math.*;

public class RandomizedSet {
    private List<Integer> vals;

    private Map<Integer, Integer> valIndexMap;

    private Random random;

    public RandomizedSet() {
        this.vals = new ArrayList<Integer>();
        this.valIndexMap = new HashMap<Integer, Integer>();
        this.random = new Random();
    }

    public boolean insert(int val) {
        if (this.valIndexMap.containsKey(val))
            return false;
        this.vals.add(val);
        this.valIndexMap.put(val, vals.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!valIndexMap.containsKey(val))
            return false;
        int i = valIndexMap.get(val);
        if (i != this.vals.size() - 1) {
            int last = this.vals.get(this.vals.size() - 1);
            this.vals.set(i, last);
            this.valIndexMap.put(last, i);
        }
        this.valIndexMap.remove(val);
        this.vals.remove(this.vals.size() - 1);
        return true;
    }

    public int getRandom() {
        if (this.vals.size() == 0)
            return -1;
        return this.vals.get(this.random.nextInt(this.vals.size()));
    }
}
