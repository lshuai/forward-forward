import java.util.*;
import java.util.stream.*;

public class EditDistance {
    public static void main(String[] args) {
        System.out.println(new EditDistance().minDistance("a", "b"));
    }

    public int minDistance(String word1, String word2) {
         if (word1 == null || word2 == null)
             return -1;

         int[][] dp = new int[word1.length() + 1][word2.length() + 1];
         for (int i = 1; i < word2.length() + 1; i++)
             dp[0][i] = i;
         for (int i = 1; i < word1.length() + 1; i++)
             dp[i][0] = i;
         for (int i = 0; i < word1.length(); i++) {
             for (int j = 0; j < word2.length(); j++) {
                 if (word1.charAt(i) == word2.charAt(j))
                     dp[i + 1][j + 1] = dp[i][j];
                 else 
                     dp[i + 1][j + 1] = Math.min(dp[i][j] + 1, Math.min(dp[i][j + 1] + 1, dp[i + 1][j] + 1));
             }
         }

         return dp[word1.length()][word2.length()];
     }
}
