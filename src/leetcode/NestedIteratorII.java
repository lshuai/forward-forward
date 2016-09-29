package leetcode;
import java.util.*;

public class NestedIteratorII implements Iterator<Integer> {

    private Stack<NestedInteger> stack;

    public NestedIteratorII(List<NestedInteger> nestedList) {
        this.stack = new Stack<NestedInteger>();
        for (int i = nestedList.size() - 1; i >= 0; i--)
            this.stack.push(nestedList.get(i));
    }

    @Override
    public Integer next() {
        if (this.hasNext())
            return this.stack.pop().getInteger();
        return null;
    }

    @Override
    public boolean hasNext() {
        while (!this.stack.isEmpty()) {
            NestedInteger curTop = stack.peek();
            if (stack.peek().isInteger())
                return true;
            stack.pop();
            List<NestedInteger> curTopList = curTop.getList();
            for (int i = curTopList.size() - 1; i >= 0; i--) 
                stack.push(curTopList.get(i));
        }
        return false;
    }
}
