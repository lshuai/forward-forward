package leetcode;
import java.util.*;

public class BullsAndCows {
    public static void main(String[] args) {
        System.out.println(new BullsAndCows().getHint("11", "11"));
    }
    
    public String getHint(String secret, String guess) {
        int[] m = new int[256];
        int bulls = 0, cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) 
                bulls++;
            else 
                m[secret.charAt(i)]++;
        }
        for (int i = 0; i < guess.length(); i++) {
            if (secret.charAt(i) != guess.charAt(i) && m[guess.charAt(i)] > 0) {
                cows++;
                m[guess.charAt(i)]--;
            }
        }
        
        return bulls + "A"  + cows + "B";
    }
}
