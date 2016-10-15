package snapchat;
import java.util.*;
import java.util.stream.*;

class TrieNode {
    public String quadIp;
    public List<TrieNode> children;

    public TrieNode(String quadIp) {
        this.quadIp = quadIp;
        this.children = new LinkedList<TrieNode>();
    }

    public boolean isEnd() {
        return this.children.size() == 0;
    }
}

public class BadIpStore {
	
	public static void main(String[] args) {
		BadIpStore store = new BadIpStore();
		System.out.println(store.storeBadIp("7.0.0.0/8"));
		System.out.println(store.storeBadIp("6.7.8.134/8"));
		System.out.println(store.storeBadIp("6.7.8.134/8"));
		//System.out.println(store.isBadIp("7.1.0.2"));
		System.out.println(store.isBadIp("6.7.7.14"));
	}

    private final TrieNode root;

    public BadIpStore() {
        this.root = new TrieNode(null);
    }

    public boolean storeBadIp(String ip) {
        List<String> quadIps = breakIp(ip);
        int i = 0;
        TrieNode cur = this.root;
        while (i < quadIps.size()) {
            String quadIp = quadIps.get(i);
            for (TrieNode child: cur.children) {
                if (child.quadIp.equals(quadIp)) {
                    if (child.isEnd())
                        return false;
                    cur = child;
                    i++;
                    continue;
                }
            }
            TrieNode newNode = new TrieNode(quadIp);
            cur.children.add(newNode);
            cur = newNode;
            i++;
        }
        return true;
    }

    public boolean isBadIp(String ip) {
        if (ip == null || ip.length() == 0)
            return false;
        int i = 0;
        TrieNode cur = this.root;
        List<String> quadIps = Arrays.asList(ip.split("\\."));
        while (i < quadIps.size()) {
            String quadIp = quadIps.get(i);
            boolean none = true;
            for (TrieNode child: cur.children) {
            	if (child.quadIp.equals(quadIp)) {
            		if (child.isEnd())
            			return true;
            		i++;
            		cur = child;
            		none = false;
            		break;
            	}
            }
            if (none)
            	return false;
        }
        return false;
    }

    private List<String> breakIp(String ip) {
        int matchNum = Integer.parseInt(ip.split("/")[1]) / 8;    
        return Stream.of(ip.split("/")[0].split("\\.")).limit(matchNum).collect(Collectors.toList()); 
    }
}

