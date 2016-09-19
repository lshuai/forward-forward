package pinterest;
import java.util.*;

class TreeNode {
    public int val;
    public List<TreeNode> children;

    public TreeNode(int val) {
        this.val = val;
        this.children = new LinkedList<TreeNode>();
    }
}

public class TreeSerialization {
	public static void main(String[] args) {
		TreeNode root = new TreeSerialization().deserialize("(1(2(5)(6))(3(7))(4(8)))");
		printTree(root);
		zigZagPrint(root);
		System.out.println(new TreeSerialization().serialize(root));
	}

    public String serialize(TreeNode root) {
        if (root == null)
            return "";

        StringBuilder sb = new StringBuilder();
        serializeAux(sb, root);
        return sb.toString();
    }
    
    private void serializeAux(StringBuilder sb, TreeNode root) {
        if (root == null)
            return;

        sb.append("(").append(root.val);
        if (root.children != null) {
            for (TreeNode child: root.children)
                serializeAux(sb, child);
        }
        sb.append(")");
    }
    
    public TreeNode deserialize(String treeStr) {
        if (treeStr == null || treeStr.length() == 0)
            return null;

        TreeNode root = null;
        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        for (int i = 0; i < treeStr.length(); i++) {
            char cur = treeStr.charAt(i);
            if (cur == ')') {
                TreeNode top = nodeStack.pop();
                if (!nodeStack.isEmpty())
                    nodeStack.peek().children.add(top);
                else
                    root = top;
            }
            else if (cur != '(')
                nodeStack.push(new TreeNode(Character.getNumericValue(cur)));
        }

        return root;
    }
    
    public static void zigZagPrint(TreeNode root) {
        if (root == null)    	
            return;

        Stack<TreeNode> cur = new Stack<TreeNode>();
        cur.push(root);
        Stack<TreeNode> lower = new Stack<TreeNode>();
        while (!cur.isEmpty()) {
            while (!cur.isEmpty()) {
                TreeNode pop = cur.pop();
                System.out.print(pop.val + " ");
                for (TreeNode child: pop.children)
                    lower.push(child);
            }
            cur = lower;
            lower = new Stack<TreeNode>();
            System.out.println();
        }
    }
    
    public static void printTree(TreeNode root) {
    	if (root == null)
    		return;
    	
    	Queue<TreeNode> cur = new LinkedList<TreeNode>(Arrays.asList(root));
    	Queue<TreeNode> lower = new LinkedList<TreeNode>();
    	while (!cur.isEmpty()) {
    		while (!cur.isEmpty()) {
    			TreeNode poll = cur.poll();
    			System.out.print(poll.val + " ");
    			for (TreeNode child: poll.children)
    				lower.offer(child);
    		}
    		System.out.println();
    		cur = lower;
    		lower = new LinkedList<TreeNode>();
    	}
    }
}
