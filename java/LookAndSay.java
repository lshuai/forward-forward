import java.util.*;
import java.util.stream.*;

public class LookAndSay {
    public static void main(String[] args) {
       System.out.println(new LookAndSay().findNth(6)); 
    }

    public String findNth(int n) {
        if (n <= 0)
            return "";
        if (n == 1)
            return "1";
        StringBuilder prev = new StringBuilder("1");
        for (int i = 2; i <= n; i++) {
            StringBuilder cur = new StringBuilder(); 
            int right = 0;
            int count = 0;
            int left = 0;
            while (right < prev.length()) {
                char curVal = prev.charAt(right);
                while (right < prev.length() && prev.charAt(right) == prev.charAt(left)) 
                    right++;
                cur.append(right - left).append(curVal);
                left = right;
            }
            prev = cur;
        }

        return prev.toString();
    }
}
