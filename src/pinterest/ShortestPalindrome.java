package pinterest;

public class ShortestPalindrome {
    public static void main(String[] args) {
        System.out.println(new ShortestPalindrome().shortestPalindrome("babbbabbaba"));
    }
    
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return "";
        if (s.length() == 1)
            return s;
        
        int mid = s.length() / 2;
        if (s.length() % 2 != 0) {
            String toAdd = scan(s, mid, mid);
            if (toAdd != null)
                return new StringBuilder(toAdd).append(s).toString();
        }
        if (s.charAt(mid) == s.charAt(mid - 1)) {
            String toAdd = scan(s, mid - 1, mid);
            if (toAdd != null)
                return new StringBuilder(toAdd).append(s).toString();
        }
        for (int i = mid - 1; i > 0; i--) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                String toAdd = scan(s, i - 1, i);
                if (toAdd != null)
                    return new StringBuilder(toAdd).append(s).toString();
            }
            else {
                String toAdd = scan(s, i, i);
                if (toAdd != null)
                    return new StringBuilder(toAdd).append(s).toString();
            }
        }
        return new StringBuilder(s.substring(1, s.length())).reverse().append(s).toString();
    }
    
    private static String scan(String s, int l, int r) {
        while (l >= 0) {
            if (s.charAt(l) != s.charAt(r))
                break;
            l--;
            r++;
        }
        if (l >= 0)
            return null;

        return new StringBuilder(s.substring(r, s.length())).reverse().toString();
    }
}
