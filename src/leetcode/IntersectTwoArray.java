package leetcode;
import java.util.*;

public class IntersectTwoArray {
    public static void main(String[] args) {
        IntersectTwoArray i = new IntersectTwoArray();
        print(i.intersection(new int[] {1, 2,2, 1}, new int[] {2,2}));
    }
    public static void print(int[] nums) {
        for (int n: nums) 
            System.out.print(n + " ");
    }
    
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums1.length == 0)    
            return new int[0];

        Set<Integer> set = new HashSet<Integer>();
        for (int n: nums1)
            if (!set.contains(n))
                set.add(n);
        Set<Integer> res = new HashSet<Integer>();
        for (int n: nums2)
            if (set.contains(n) && !res.contains(n))
                res.add(n);
        int[] arrayRes = new int[res.size()];
        int i = 0;
        for (int n: res) 
            arrayRes[i++]  = n;
        
        return arrayRes;
    }
}
