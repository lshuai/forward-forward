import java.util.*;

public class DecodeWays {
    public static void main(String[] args) {
        DecodeWays test = new DecodeWays();
        System.out.println(!Character.isDigit('1'));
        System.out.println(test.numDecodings("27"));
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || !Character.isDigit(s.charAt(0)) || s.charAt(0) == '0')
            return 0;

        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)))
                return 0;
            int xy = s.charAt(i) - '0' + 10 * (s.charAt(i - 1) - '0');
            if ((xy > 26 || xy < 10) && s.charAt(i) == '0')
                return 0;
            if (s.charAt(i) != '0')
                dp[i + 1] = dp[i];
            if (xy <= 26 && xy >= 10)
                dp[i + 1] += dp[i - 1];
        }
         
        return dp[s.length()];
    }
}
