package snapchat;

import java.util.*;
import java.util.stream.Collectors;

public class AdjacencyLists {
	private final Map<Integer, Set<Integer>> adjLists;
	private int edgeNum;
	
	public AdjacencyLists() {
		this.adjLists = new HashMap<Integer, Set<Integer>>();
		this.edgeNum = 0;
	}
	
	public boolean addEdge(int a, int b) {
		if (a == b)
			return false;

		int min = Math.min(a, b);
		int max = Math.max(a, b);
		if (this.adjLists.containsKey(min) && this.adjLists.get(min).contains(max))
			return false;
		if (!this.adjLists.containsKey(min)) {
		    Set<Integer> adjSet = new HashSet<Integer>();
			this.adjLists.put(min, adjSet);
		}
		this.adjLists.get(min).add(max);
		this.edgeNum++;
		return true;
	}
	
	public int numberOfEdges() {
		return this.edgeNum;
	}
	
	public boolean IsDirectlyConnected(int a, int b) {
		int min = Math.min(a, b);
		int max = Math.max(a, b);
		return this.adjLists.containsKey(min) && this.adjLists.get(min).contains(max);
	}
	
	public String deserialize() {
		Set<String> set = new HashSet<String>();
		set.stream().collect(Collectors.toList());
		set.toArray(new String[set.size()]);
		if (this.adjLists == null || this.adjLists.size() == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int k: this.adjLists.keySet()) {
			sb.append(k).append(":").append(String.join("", 
					this.adjLists.get(k)
					.stream()
					.map(i -> i.toString())
					.collect(Collectors.toList()))).append(",");
		}
		return sb.deleteCharAt(sb.length() - 1).toString();
	}
}
