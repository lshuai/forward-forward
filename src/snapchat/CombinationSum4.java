package snapchat;
import java.util.*;

public class CombinationSum4 {
    public static void main(String[] args) {
        System.out.println(new CombinationSum4().combinationSum4(new int[] {1,2,3}, 12));
        System.out.println(new CombinationSum4().dp(new int[] {1,2,3}, 12));
        System.out.println( 1234 % 100);
    }


    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] comb = new int[] {0};
        Arrays.sort(nums);
        aux(nums, target, 0, comb);
        return comb[0];
    }

    private void aux(int[] nums, int target, int sum, int[] comb) {
        if (sum == target) {
            comb[0]++;
            return;
        }
        if (sum > target)
            return;

        for (int i = 0; i < nums.length; i++) 
            aux(nums, target, sum + nums[i], comb);
    }
    
    public int dp(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        return comb(nums, target, map);
    }
    
    private int comb(int[] nums, int target, Map<Integer, Integer> map) {
        if (target < 0)
            return 0;
        if (target == 0)
            return 1;
        
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int delta = target - nums[i];
            if (delta >= 0) {
                int tmpComb = 0;
                if (!map.containsKey(delta)) {
                    tmpComb = comb(nums, delta, map);
                    map.put(delta, tmpComb);
                }
                sum += map.get(delta);
            }
        }
        return sum;
    }
}
