import java.util.*;
import java.util.stream.*;

public class WordCount {
    public static void main(String[] args) {
        System.out.println(new WordCount().count("!! a !!")); 
    }

    public int count(String words) {
        if (words == null || words.length() == 0)
            return 0;

        int count = 0;
        for (int i = 0; i < words.length(); i++) {
            while (i < words.length() && !Character.isLetter(words.charAt(i)))
                i++;
            if (i < words.length())
                count++;
            while (i < words.length() && Character.isLetter(words.charAt(i)))
                i++;
        }
        return count;
    } 
}
