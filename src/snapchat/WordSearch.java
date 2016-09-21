package snapchat;
import java.util.*;
import java.util.stream.*;

public class WordSearch {
	public static void main(String[] args) {
		String[] input1 = new String[] {"CAT", "DOG"};
		String[] input2 = new String[] {"GAT", "DOC", "CD", "GOAT", "BAD", "COOL"};
		WordSearch ws = new WordSearch(Arrays.asList(input2));
		System.out.println(ws.search(input1));
	}
	
	
    private final Map<String, List<List<String>>> anagramsMap;

    public WordSearch(List<String> words) {
        this.anagramsMap = buildMap(words);
    }

    public List<List<String>> search(String[] words) {
        char[] sortedArray = String.join("", words).toCharArray();  
        Arrays.sort(sortedArray);
        String sorted = new String(sortedArray);
        if (this.anagramsMap.containsKey(sorted))
            return this.anagramsMap.get(sorted);
        return null;
    }

    private static Map<String, List<List<String>>> buildMap(List<String> words) {
        Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
        List<String> path = new LinkedList<String>();
        buildMapAux(0, map, words, path);
        return map;
    }

    private static void buildMapAux(int start, Map<String, List<List<String>>> map, List<String> words, List<String> path) {
        if (start == words.size())
            return;
        
        for (int i = start; i < words.size(); i++) {
            path.add(words.get(i));
            addToMap(map, path);
            buildMapAux(i + 1, map, words, path);
            path.remove(path.size() - 1);
        }
    }

    private static void addToMap(Map<String, List<List<String>>> map, List<String> path) {
    	List<String> copy = new LinkedList<String>(path);
        char[] sortedArray = String.join("", copy).toCharArray();
        Arrays.sort(sortedArray);
        String sorted = new String(sortedArray);
        if (!map.containsKey(sorted))
            map.put(sorted, new LinkedList<List<String>>(Arrays.asList(copy)));
        else 
            map.get(sorted).add(copy);
    }
}
