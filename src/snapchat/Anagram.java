package snapchat;
import java.util.*;
import java.util.stream.*;

public class Anagram {
	public static void main(String[] args) {
		String a = "aab";
		String b = "aadaadaadaadaab";
		System.out.println(new Anagram().isAnagramIn(a, b));
	}
	
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return null;

        Map<String, List<String>> anaGroupMap = new HashMap<String, List<String>>();
        for (String str: strs) {
            char[] sortedArray = str.toCharArray();
            Arrays.sort(sortedArray);
            String sortedStr = new String(sortedArray);
            if (!anaGroupMap.containsKey(sortedStr)) 
                anaGroupMap.put(sortedStr, new LinkedList<String>(Arrays.asList(str)));
            else
                anaGroupMap.get(sortedStr).add(str);
        }
        
        List<List<String>> res = new LinkedList<List<String>>();
        for (String key: anaGroupMap.keySet())
            res.add(anaGroupMap.get(key));
        return res;
    }

    public boolean isAnagramIn(String a, String b) {
        if (a == null || b == null || a.length() > b.length())
            return false;
        Map<Character, Integer> deltaMap  = new HashMap<Character, Integer>();
        for (int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            if (!deltaMap.containsKey(ch)) 
                deltaMap.put(ch, 1);
            else
                deltaMap.put(ch, deltaMap.get(ch) + 1);
        }
        int delta = a.length();
        for (int i = 0; i < a.length(); i++) {
            char ch = b.charAt(i);
            if (deltaMap.containsKey(ch)) {
                int count = deltaMap.get(ch) - 1;
                if (count >= 0)
                    delta--;
                else if (count < 0)  
                    delta++;
                deltaMap.put(ch, count);
            }
        }
        if (delta == 0)
            return true;
        int l = 1;
        int r = a.length();
        while (r < b.length()) {
            char lch = b.charAt(l);
            if (deltaMap.containsKey(lch)) {
                int count = deltaMap.get(lch) + 1;
                delta = Math.abs(count) < Math.abs(deltaMap.get(lch)) ? delta + 1 : delta - 1; 
                deltaMap.put(lch, count);
            }
            char rch = b.charAt(r);
            if (r != l && deltaMap.containsKey(rch)) {
                int count = deltaMap.get(rch) - 1;
                delta = Math.abs(count) < Math.abs(deltaMap.get(rch)) ? delta + 1 : delta - 1;
                deltaMap.put(rch, count);
            }
            if (delta == 0)
                return true;
            l++;
            r++;
        }
        return false;
    }
}