package pinterest;
import java.util.*;
import java.util.stream.*;

public class PilesOfStoneGame {
    public static void main(String[] args) {
        PilesOfStoneGame game = new PilesOfStoneGame();
        System.out.println(game.canFirstPlayerWin(1, 2, 1));
    }
    
    public boolean canFirstPlayerWin(int p1, int p2, int k) {
        if (p1 < 0 || p2 < 0 || (p1 == 0 && p2 == 0))
            return false;

        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put(1 + "," + 0, true);
        map.put(0 + "," + 1, true);
        map.put(0 + "," + 0, false);
        return aux(p1, p2, k, map);
    }

    private boolean aux(int p1, int p2, int k, Map<String, Boolean> map) {
        String curPair = p1 + "," + p2;
        if (map.containsKey(curPair))
            return map.get(curPair);

        for (int i = 1; i <= Math.min(k, p1); i++) {
            if (aux(p1 - i, p2, k, map)) {
                map.put(curPair, true);
                return true;
            }
            map.put(curPair, false);
        }

        for (int i = 1; i < Math.min(k, p2); i++) {
            if (aux(p1, p2 - i, k, map)) {
                map.put(curPair, true);
                return true;
            }
            map.put(curPair, false);
        }
        return false;
    }
}
