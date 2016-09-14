package snapchat;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

public class FlattenBtToList {
    private static TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null)
            return;

        TreeNode right = root.right;
        if (prev != null) {
            prev.left = null;
            prev.right = root;
        }
    	prev = root;
        flatten(root.left);
        flatten(right);
    }
}
