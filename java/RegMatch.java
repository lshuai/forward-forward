import java.util.*;
import java.util.stream.*;

public class RegMatch {
    public static void main(String[] args) {
        System.out.println(new RegMatch().isMatch("aa", ".*"));
    }

    public boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return false;
        
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) 
            dp[0][i + 1] = p.charAt(i) == '*' && dp[0][i - 1];

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')
                    dp[i + 1][j + 1] = dp[i][j];
                else if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.')
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    else 
                        dp[i + 1][j + 1] = dp[i + 1][j] || dp[i + 1][j - 1] || dp[i][j + 1];
                }
            }
        }

        return dp[s.length()][p.length()];
    } 
}
