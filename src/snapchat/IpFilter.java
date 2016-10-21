package snapchat;
import java.util.*;

class IpNode {
    public boolean val;
    public List<IpNode> children; // max length should be 2

    public IpNode(boolean val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}

public class IpFilter {
    public static void main(String[] args) {
        int[] ip = toBoolIp(new int[]{8, 7, 10, 1});
        for (int i = 0; i < ip.length; i++)  {
            System.out.print(ip[i]);
            if ((i + 1) % 8 == 0 && i != 31)
                System.out.print(".");
        }
        System.out.println();
        IpFilter filter = new IpFilter();
        filter.addFilter(new int[] {7, 0, 0, 0}, 16);
        System.out.println(filter.isBadIp(new int[] {7, 0, 23, 32}));
    }
    private IpNode root;

    public IpFilter() {
        this.root = new IpNode(false);
    }
    
    public boolean addFilter(int[] ip, int len) {
        if (ip.length != 4 || len <= 0 || len > 32)
            return false;
        return this.addFilter(convertToBoolIp(ip), len);
    }
    
    private static boolean[] convertToBoolIp(int[] ip) {
        boolean[] binaryIp = new boolean[32];
        int end = 7;
        for (int i = 0; i < ip.length; i++) {
            int cur = ip[i];
            int e = end;
            while (cur != 0) {
                binaryIp[e--] = cur % 2 == 1;
                cur = cur / 2;
            }
            end += 8;
        }
        return binaryIp;
    }

    public static int[] toBoolIp(int[] ip) {
        int[] binaryIp = new int[32];
        int end = 7;
        for (int i = 0; i < ip.length; i++) {
            int cur = ip[i];
            int e = end;
            while (cur != 0) {
                binaryIp[e--] = cur % 2;
                cur = cur / 2;
            }
            end += 8;
        }
        return binaryIp;
    }

    public boolean addFilter(boolean[] ip, int len) {
        if (ip == null || ip.length != 32 || len > 32 || len <= 0)
            return false;

        IpNode cur = this.root;
        for (int i = 0; i < len; i++) {
            IpNode next = findInChildren(cur, ip[i]);           
            if (next == null) {
                if (cur != root && cur.children.size() == 0)
                    return false;
                next = new IpNode(ip[i]);
                cur.children.add(next);
            }
            cur = next;
        }
        if (cur.children.size() != 0)
            return false;
        return true;
    }
    
    public boolean isBadIp(int[] ip) {
        if (ip == null || ip.length != 4)
            return true;
        
        return isBadIp(convertToBoolIp(ip));
    }

    public boolean isBadIp(boolean[] ip) {
        if (ip == null || ip.length != 32)
            return false;
        IpNode cur = this.root;
        for (int i = 0; i < ip.length; i++) {
            IpNode next = findInChildren(cur, ip[i]);
            if (next == null)
                return cur.children.size() == 0;
            cur = next;
        }
        return true;
    }

    private static IpNode findInChildren(IpNode n, boolean val) {
        for (IpNode ipNode: n.children) {
            if (ipNode.val == val)
                return ipNode;
        }
        return null;
    }
}
