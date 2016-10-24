package snapchat;
import java.util.*;

class DpNode {
    public int prevIndex;
    public String val;

    public DpNode(String val, int prevIndex) {
        this.val = val;
        this.prevIndex = prevIndex;
    }
}

public class WordBreakII {
    public static void main(String[] args) {
        WordBreakII wb = new WordBreakII();
        Set<String> wordDict = new HashSet<String>(Arrays.asList("cat", "cats", "and", "sand", "dog"));

        List<String> res = wb.wordBreak("catsanddog", wordDict);
        System.out.println(res);
    }
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<List<DpNode> >dp = new ArrayList<>(s.length() + 1);
        for (int i = 0; i < s.length() + 1; i++) 
            dp.add(i == 0 ? new LinkedList<>() : null);
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (dp.get(j) != null && wordDict.contains(s.substring(j, i))) {
                    if (dp.get(i) == null) 
                        dp.set(i, new LinkedList<>(Arrays.asList(new DpNode(s.substring(j, i), j))));
                    else
                        dp.get(i).add(new DpNode(s.substring(j, i), j));
                }
            }
        }

        if (dp.get(s.length()) == null)
            return new LinkedList<>();
        List<String> res = new LinkedList<String>();
        buildRes(new LinkedList<String>(), s.length(), dp, res);
        return res;
    }

    private void buildRes(LinkedList<String> bt, int i, List<List<DpNode>> dp, List<String> res) {
        if (i == 0) {
            res.add(String.join(" ", bt));
        }

        for (DpNode dpNode: dp.get(i)) {
            bt.addFirst(dpNode.val);
            buildRes(bt, dpNode.prevIndex, dp, res);
            bt.remove(0);
        }
    }
}