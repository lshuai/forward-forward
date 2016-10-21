package snapchat;
import java.util.*;

/*
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return false;
        
        List<TreeNode> cur = new ArrayList<>();
        cur.add(root);
        List<TreeNode> next = new ArrayList<>();
        while (!cur.isEmpty()) {
            int l = 0;
            int r = cur.size() - 1;
            while (l <= r) {
                TreeNode left = cur.get(l++);
                TreeNode right = cur.get(r--);
                if (!isLeftRight(left, right) || !isRightLeft(left, right))
                    return false;
            }
            for (TreeNode n: cur) {
                if (n.left != null)
                    next.add(n.left);
                if (n.right != null)
                    next.add(n.right);
            }
            cur = next;
            next = new ArrayList<>();
        }
        return true;
    }
    
    private static boolean isLeftRight(TreeNode a, TreeNode b) {
        return a.left != null && b.right != null && a.left.val == b.right.val || (a.left == null && b.right == null);
    }
    
    private static boolean isRightLeft(TreeNode a, TreeNode b) {
        return a.right != null && b.left != null && a.right.val == b.left.val || (a.right == null && b.left == null);
    }
}
*/
