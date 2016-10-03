package snapchat;
import java.util.*;

public class NQueensII {
    public int totalNQueens(int n) {
        if (n <= 0)
            return 0;
        
        int[] res = new int[1];
        aux(0, n, new boolean[n], new boolean[2*n], new boolean[2*n], res); 
        return res[0];
    }

    private static void aux(int r, int n, boolean[] cols, boolean[] d1, boolean[] d2, int[] res) {
        if (r == n) {
            res[0]++;
            return;
        }

        for (int c = 0; c < n; c++) {
            int id1 = r - c + n;
            int id2 = r + c;
            if (cols[c] || d1[id1] || d2[id2])
                continue;
            cols[c] = true;
            d1[id1] = true;
            d2[id2] = true;
            aux(r + 1, n, cols, d1, d2, res);
            cols[c] = false;
            d1[id1] = false;
            d2[id2] = false;
        }
    }
}
