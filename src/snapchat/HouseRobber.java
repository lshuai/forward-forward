package snapchat;
import java.util.*;
import java.util.stream.*;

public class HouseRobber {
    public static void main(String[] args) {
        System.out.println(new HouseRobber().dpRob(new int[] {1,2,3,4,5,6}));
    }

    public int dpRob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[][] dp = new int[nums.length + 1][2];
        int max1 = 0;
        int max0 = 0;
        for (int i = 0; i < nums.length; i++) {
            int max00 = Math.max(max0, max1);
            int max11 = nums[i] + max0;
            max0 = max00;
            max1 = max11;
        }
       
        return Math.max(max0, max1); 
    }

    // 1 2 3 4 5 6
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++)
                dp[i][j] = -1;
        }
        return robAux(0, nums.length - 1, nums, dp);
    }

    private static int robAux(int l, int r, int[] nums, int[][] dp) {
        if (l > r)
            return 0;
        if (l == r)
            return nums[l];

        int max = 0;
        for (int i = l; i <= r; i++) {
            int rightEnd = r;
            if (i == 0 && r == nums.length - 1)
                rightEnd = r - 1;
            int leftStart = l;
            if (i == nums.length - 1 && l == 0)
                leftStart = 1;
            int left = 0;
            if (l <= i - 2) {
                if (dp[l][i - 2] == -1)
                    dp[l][i - 2] = robAux(leftStart, i - 2, nums, dp);
                left = dp[l][ i - 2];
            }
            int right = 0;
            if (r >= i + 2) {
                if (dp[i + 2][r] == -1)
                    dp[i + 2][r] = robAux(i + 2, rightEnd, nums, dp);
                left = dp[i + 2][r];
            }
            max = Math.max(max, nums[i] + left + right); 
        }

        return max;
    }
}
