package leetcode;
import java.util.*;
import java.util.stream.*;

public class PermutationII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        Arrays.sort(nums); 
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        aux(0, nums, res);
        return res; 
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void aux(int s, int[] nums, List<List<Integer>> res) {
        if (s == nums.length) {
            List<Integer> tmpList = new ArrayList<>(nums.length);
            for (int n: nums)
                tmpList.add(n);
            res.add(tmpList);
        }

        for (int i = s; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            swap(nums, s, i);
            aux(s + 1, nums, res);
            swap(nums, s, i);
        }
    }
}
