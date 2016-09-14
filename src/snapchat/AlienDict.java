package snapchat;
import java.util.*;
import java.util.stream.*;

public class AlienDict {
	public static void main(String[] args) {
		System.out.println(new AlienDict().alienOrder(new String[] {
		                                                             "wrt",
		                                                             "wrf",
		                                                             "er",
		                                                             "ett",
		                                                             "rftt"
		                                                            }));
	}
	
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0)
            return "";
        
        // Build graph
        Map<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
        addVFromWord(graph, words[0]);
        for (int i = 1; i < words.length; i++) {
            String pre = words[i - 1];
            String cur = words[i];
            addVFromWord(graph, pre);
            addVFromWord(graph, cur);
            for (int j = 0; j < pre.length() && j < cur.length(); j++) {
                if (pre.charAt(j) != cur.charAt(j) && !graph.get(pre.charAt(j)).contains(cur.charAt(j))) 
                    graph.get(pre.charAt(j)).add(cur.charAt(j));
            }
        }

        // Toplogical sorting via DFS
        Stack<Character> stack = new Stack<Character>();
        Set<Character> visited = new HashSet<Character>();
        for (Character ch: graph.keySet()) {
            if (visited.contains(ch)) continue;
            if(!dfs(stack, visited, new HashSet<Character>(), graph, ch))
                return "";
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());
        return sb.toString();
    }

    private static boolean dfs(Stack<Character> stack, 
        Set<Character> visited, 
        Set<Character> visiting, 
        Map<Character, Set<Character>> graph, 
        char cur) {
        if (visited.contains(cur))
            return true;
        if (visiting.contains(cur))
            return false;
        visiting.add(cur);
        for (Character ch: graph.get(cur)) {
            if (!dfs(stack, visited, visiting, graph, ch))
                return false;
        }
        visiting.remove(cur);
        visited.add(cur);
        stack.push(cur);
        return true;
    }

    private static void addVFromWord(Map<Character, Set<Character>> graph, String word) {
    	for (int i = 0; i < word.length(); i++) {
    		char c = word.charAt(i);
            if (!graph.containsKey(c))
                graph.put(c, new HashSet<Character>());
        }
    }
}
