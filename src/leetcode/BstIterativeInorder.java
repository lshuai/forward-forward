package leetcode;
import java.util.*;

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class BstIterativeInorder {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.right = new TreeNode(8);
        root.right.left = new TreeNode(6);
        inorder(root);
    }
    
    public static void inorder(TreeNode root) {
        if (root == null) 
            return;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        Set<Integer> visited = new HashSet<Integer>();
        stack.push(root);
        while (!stack.isEmpty()) {
            while (stack.peek().left != null && !visited.contains(stack.peek().left.val))
                stack.push(stack.peek().left);
            TreeNode pop = stack.pop();
            System.out.print(pop.val + " ");
            visited.add(pop.val);
            if (pop.right != null)
                stack.push(pop.right);
        }
    }
}
