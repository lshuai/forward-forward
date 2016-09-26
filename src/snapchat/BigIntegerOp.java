package snapchat;
import java.util.*;

public class BigIntegerOp {
    // a>=0 && b>=0
    public String add(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int ia = i >= 0 ? Character.getNumericValue((a.charAt(i))) : 0;
            int jb = j >= 0 ? Character.getNumericValue((b.charAt(j))) : 0;
            int digit = (ia + jb + carry) % 10;
            sb.insert(0, Integer.toString(digit));
            carry = (ia + jb + carry) / 10;
            i--;
            j--;
        }
        return sb.toString();
    }
    
    // a >= b; a >=0 && b >= 0
    public String subtract(String a, String b) {
        StringBuilder ab = new StringBuilder(a);     
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1;
        int k = b.length() - 1;
        int is = 0;
        while(i >= is && k >= 0) {
            int ia = Character.getNumericValue(ab.charAt(i));
            int kb = Character.getNumericValue(b.charAt(k));
            int digit = ia >= kb ? ia - kb : 10 + ia - kb;
            res.insert(0, Integer.toString(digit));
            if (ia < kb) {
                int j = i - 1;
                while (j >= 0 && ab.charAt(j) == '0') {
                    ab.replace(j, j + 1, "9"); 
                    j--;
                }
                if (ab.charAt(j) == '1' && j == 0)
                    is++;
            }
            i--;
            k--;
        }
        res.insert(0, ab.substring(is, i + 1));
        is = 0;
        while (res.charAt(is) == '0')
            is++;
            
        return res.substring(is).toString(); 
    }
    
    public static void main(String[] args) {
        System.out.println(new BigIntegerOp().add("89", "202"));
        System.out.println(new BigIntegerOp().subtract("1000", "990"));
        System.out.println('7' - '1');
    }
}
