package snapchat;
import java.util.*;

public class BigIntegerOp {
    public String add(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 && j >= 0) {
            int ia = Character.getNumericValue((a.charAt(i)));
            int jb = Character.getNumericValue((b.charAt(j)));
            int digit = (ia + jb + carry) % 10;
            sb.insert(0, Integer.toString(digit));
            carry = (ia + jb + carry) / 10;
            i--;
            j--;
        }
        while (i >= 0) {
            int digit  = (Character.getNumericValue((a.charAt(i))) + carry) % 10;
            carry = (Character.getNumericValue((a.charAt(i)) + carry)) / 10;
            sb.insert(0, Integer.toString(digit));
            i--;
        }
        while (j >= 0) {
            int digit  = (Character.getNumericValue((b.charAt(j))) + carry) % 10;
            carry = (Character.getNumericValue((b.charAt(j))) + carry) / 10;
            sb.insert(0, Integer.toString(digit));
            j--;
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(new BigIntegerOp().add("89", "202"));
    }
}
