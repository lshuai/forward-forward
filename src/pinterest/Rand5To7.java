package pinterest;
import java.util.*;

public class Rand5To7 {
    public static void main(String[] args) {
        System.out.println(rand5());
        System.out.println(rand7());
    }

    private static Random rand = new Random();

    public static int rand7() {
        while (true) {
            int random = 5 * rand5() + rand5();
            if (random < 21)
                return random % 7;
        }
    }
    public static int rand5() {
        return rand.nextInt(5);
    }
}
