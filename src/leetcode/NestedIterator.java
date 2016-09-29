package leetcode;
import java.util.*;
  // This is the interface that allows for creating nested lists.
  // You should not implement it, or speculate about its implementation
interface NestedInteger {
 
      // @return true if this NestedInteger holds a single integer, rather than a nested list.
      public boolean isInteger();
 
      // @return the single integer that this NestedInteger holds, if it holds a single integer
      // Return null if this NestedInteger holds a nested list
      public Integer getInteger();
 
      // @return the nested list that this NestedInteger holds, if it holds a nested list
      // Return null if this NestedInteger holds a single integer
      public List<NestedInteger> getList();
  }

public class NestedIterator implements Iterator<Integer> {

    private Iterator<Integer> iter;

    public NestedIterator(List<NestedInteger> nestedList) {
        List<Integer> flat = new LinkedList<Integer>();
        for (NestedInteger nestedInteger: nestedList)
            populateList(nestedInteger, flat);
        this.iter = flat.iterator();
    }

    private static void populateList(NestedInteger nestedInteger, List<Integer> flat) {
        if (nestedInteger.isInteger()) {
            flat.add(nestedInteger.getInteger());
            return;
        }

        for (NestedInteger childNested: nestedInteger.getList())
            populateList(childNested, flat);
    }

    @Override
    public Integer next() {
        return this.iter.next(); 
    }

    @Override
    public boolean hasNext() {
        return this.iter.hasNext();
    }
}
