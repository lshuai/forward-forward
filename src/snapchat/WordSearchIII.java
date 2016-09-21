package snapchat;
import java.util.*;
import java.util.stream.*;

public class WordSearchIII {
    public static void main(String[] args) {
        String str = "CATDOG";
        List<String> words = new ArrayList<String>(Arrays.asList("GAT", "DOC", "CD", "GOAT", "BAD", "COOL"));
        System.out.println(new WordSearchIII().search(str, words));
    }

    // str length <= sum(word lengths)
    public List<List<String>> search(String str, List<String> words) {
        List<List<String>> res = new LinkedList<List<String>>();
        Set<String> btSet = new HashSet<String>();
        searchAux(res, str, words, 0, btSet, 0);
        return res;
    }

    private static void searchAux(List<List<String>> res, 
        String str,
        List<String> words, 
        int start, 
        Set<String> btSet,
        int count) {
            
        if (count == str.length()) {
            if (isAnagram(str, btSet))
                res.add(new LinkedList(btSet));
            return;
        }
        if (count > str.length()) 
            return;

        for (int i = start; i < words.size(); i++) {
            btSet.add(words.get(i));
            searchAux(res, str, words, i + 1, btSet, count + words.get(i).length());
            btSet.remove(words.get(i));
        }
    } 

    private static boolean isAnagram(String str, Set<String> btSet) {
        Map<Character, Integer> counter = new HashMap<Character, Integer>();

        for (int i = 0; i < str.length(); i++) {
            if (!counter.containsKey(str.charAt(i)))
                counter.put(str.charAt(i), 1);
            else {
                counter.put(str.charAt(i), counter.get(str.charAt(i)) + 1);
            }
        }
        for (String word: btSet) {
            for (int i = 0; i < word.length(); i++) {
                if (!counter.containsKey(word.charAt(i)))
                    return false;
                counter.put(word.charAt(i), counter.get(word.charAt(i)) - 1);
            }
        }

        return counter.values().stream().allMatch(c -> c == 0);
    }
}
