import java.util.*;
import java.util.stream.*;

public class LCSubString {
    public static void main(String[] args) {
  		String A = "tutorialhorizon";
		String B = "dynamictutorialProgramming";	
        System.out.println(new LCSubString().compute(A, B));
    }

    public String compute(String a, String b) {
        if (a == null || b == null || a.length() == 0 || b.length() == 0)
             return "";

        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < a.length(); i++)
            dp[i][0] = 0;
        for (int i = 0; i < a.length(); i++)
            dp[0][i] = 0;
        int max = 0;
        String res = "";
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) 
                if (a.charAt(i) == b.charAt(j))
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                else if (max < dp[i][j]) {
                    max = dp[i][j];
                    res = a.substring(i - max, i);
                }
        }

        return res;
    }
}
