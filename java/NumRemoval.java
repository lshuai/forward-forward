import java.util.*;

public class NumRemoval {
    public static void main(String[] args) {
        System.out.println(new NumRemoval().remove(new int[] {5,2,7,9,4}, 3)); 
    }

    public String remove(int[] nums, int n) {
        if (nums == null || nums.length == 0 || n <= 0)
            return "";

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        Set<Integer> removal = new HashSet<Integer>();
        for (int i = 1; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i] && removal.size() < n) {
                removal.add(stack.pop());
            }
            if (removal.size() == n)
                break;
            stack.push(i);
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (!removal.contains(i))
                res.append(nums[i]);
        }
        return res.toString();
    }       
}
