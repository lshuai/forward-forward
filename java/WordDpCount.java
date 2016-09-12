import java.util.*;
import java.util.stream.*;

public class WordDpCount {
    public static void main(String[] args) {
        WordDpCount wb = new WordDpCount();
        String s = "leetcode";
        HashSet<String> wordDict = new HashSet(Arrays.asList("leet", "code"));
        
        wb.wordBreak(s, wordDict);
    }

    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict.isEmpty())
            return false;

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (wordDict.contains(s.substring(j, i + 1)) && dp [j]) {
                    dp[i + 1] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
