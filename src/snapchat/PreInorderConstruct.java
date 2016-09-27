package snapchat;
import java.util.*;
import java.util.stream.*;

public class PreInorderConstruct {

    public static void main(String[] args) {
        TreeNode root = new PreInorderConstruct().build(new int[] {1,2,4,5,3,6}, new int[]{4,2,5,1,3,6});
        System.out.println(root);
    }
    
    public TreeNode build(int[] preorder, int[] inorder) {
        if (preorder == null 
            || inorder == null 
            || preorder.length == 0 
            || inorder.length == 0
            || preorder.length != inorder.length)
            return null;
        Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++)
            inorderMap.put(inorder[i], i);
        return buildAux(0, preorder.length - 1, 0, inorder.length - 1, preorder, inorder, inorderMap);
    }

    private static TreeNode buildAux(int ps, 
        int pe, 
        int is, 
        int ie, 
        int[] preorder, 
        int[] inorder, 
        Map<Integer, Integer> inorderMap) {
        if (is > ie)    
            return null;

        TreeNode root = new TreeNode(preorder[ps]);
        int inRootIndex = inorderMap.get(preorder[ps]);
        int leftLen = inRootIndex - is;
        root.left = buildAux(ps + 1, ps + leftLen, is, inRootIndex - 1, preorder, inorder, inorderMap);
        root.right = buildAux(ps + leftLen + 1, pe, inRootIndex + 1, ie, preorder, inorder, inorderMap);
        return root;
    }
}
