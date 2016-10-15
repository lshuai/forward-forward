package pinterest;
import java.util.*;


public class PhoneComb {
    public static void main(String[] args) {
        PhoneComb pc = new PhoneComb();
        List<String> res = pc.letterCombinations("23");
        System.out.println(res);
    }
    
    private static Map<Character, char[]> keyPad;
    static {
        keyPad = new HashMap<Character, char[]>();
        keyPad.put('2', new char[] {'a', 'b', 'c'});
        keyPad.put('3', new char[] {'d', 'e', 'f'});
        keyPad.put('4', new char[] {'g', 'h', 'i'});
        keyPad.put('5', new char[] {'j', 'k', 'l'});
        keyPad.put('6', new char[] {'m', 'n', 'o'});
        keyPad.put('7', new char[] {'p', 'q', 'r', 's'});
        keyPad.put('8', new char[] {'t', 'u', 'v'});
        keyPad.put('9', new char[] {'w', 'x', 'y', 'z'});
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0)
            return null;
        
        List<String> res = new LinkedList<String>();
        aux(digits, 0, new StringBuilder(), res); 
        return res;
    }

    private void aux(String digits, int i, StringBuilder sb, List<String> res) {
        if (i == digits.length()) {
            res.add(sb.toString());
            return;
        }
        if (!keyPad.containsKey(digits.charAt(i)))
            return;

        for (char ch: keyPad.get(digits.charAt(i))) {
            sb.append(ch);
            aux(digits, i + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
