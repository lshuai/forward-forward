package snapchat;
import java.util.*;
import java.util.stream.*;

public class SimpleWord { 
	 public static void main(String[] args) {
		 List<String> words = Arrays.asList("chat","ever","snapchat","snap","salesperson","per","person","sales","son","whatsoever","what","so");
		 List<String> simple = new SimpleWord().simpleWords(words);
		 for(String s: simple)
			 System.out.print(s + " ");
		 System.out.println();
	 }
	
     public List<String> simpleWords(List<String> words) {
         if (words == null || words.size() == 0)
             return null;

         Set<String> simple = new HashSet<String>();
         Set<String> all = new HashSet<String>();
         for (String word: words)
        	 if (!all.contains(word)) all.add(word);
         for (String word: words) {
             if (isCompound(word, all)) continue;
             if (!simple.contains(word))
                 simple.add(word);
         }
         return simple.stream().collect(Collectors.toList());
     }  

     private boolean isCompound(String word, Set<String> all) {
        all.remove(word); 
        boolean dp[] = new boolean[word.length() + 1];
        dp[0] = true;
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j <= i; j++) {
               if (all.contains(word.substring(j, i + 1)) && dp[j]) {
                   dp[i + 1] = true;
                   break;
               }
            }
        }
        all.add(word);
        return dp[word.length()];
     }
}
