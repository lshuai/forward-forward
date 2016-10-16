package leetcode;

public class IntegerBreak {
    public static void main(String[] args) {
        IntegerBreak ib = new IntegerBreak();
        System.out.println(ib.integerBreak(10));
    }
    // programming by intention. Hey, solving + engineering
    public int integerBreak(int n) {
        if (n <= 0)
            return -1;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int cur = 2; cur <= n; cur++) {
            int max = Integer.MIN_VALUE;
            for (int i = 1; i < cur; i++) {
                int multi = Math.max(dp[i], i) * Math.max(dp[cur - i], cur - i);
                max = Math.max(multi, max);
            }
            dp[cur] = max;
        }
        return dp[n];
    }   
}
