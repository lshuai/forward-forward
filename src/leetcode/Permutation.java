package leetcode;

import java.util.*;
import java.util.stream.*;

public class Permutation {
    public static void main(String[] args) {
    	new Permutation().permute(new int[] {1, 2, 3});
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
            
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Aux(0, nums, res);
        return res;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void Aux(int s, int[] nums, List<List<Integer>> res) {
        if (s == nums.length) {
        	ArrayList<Integer> tmp = new ArrayList<Integer>(nums.length);
        	for (int n: nums)
        		tmp.add(n);
        	res.add(tmp);
            return;
        }

        for (int i = s; i < nums.length; i++) {
            swap(nums, s, i);
            Aux(s + 1, nums, res);
            swap(nums, s, i);
        }
    }
}
