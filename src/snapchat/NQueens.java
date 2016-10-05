package snapchat;
import java.util.*;

public class NQueens {
    public static void main(String[] args) {
        List<List<String>> res = solveNQueens(4);
        for (List<String> l1: res) {
            for (String l2: l1) {
                System.out.println(l2);
            }
            System.out.println();
        }
    }
   
    public static List<List<String>> solveNQueens(int n) {
        Set<Integer> cols = new HashSet<Integer>();
        Set<Integer> d1 = new HashSet<Integer>();
        Set<Integer> d2 = new HashSet<Integer>();
        Set<String> queens = new HashSet<String>();
        List<List<String>> res = new LinkedList<List<String>>();

        aux(cols, d1, d2, queens, n, 0, res);


        return res; 
    }

    private static void aux(Set<Integer> cols, 
        Set<Integer> d1, 
        Set<Integer> d2, 
        Set<String> queens, 
        int n, 
        int r,
        List<List<String>> res) {
        if (n < 0 || n == 0)
            return;
        if (r == n) {
            buildRes(res, queens);
            return;
        }

        for (int j = 0; j < n; j++) {
            int id1 = r - j + n;
            int id2 = 2 * n - r - j - 1;
            if (cols.contains(j) || d1.contains(id1) || d2.contains(id2)) continue;
            queens.add(r + "," + j);
            cols.add(j);
            d1.add(id1);
            d2.add(id2);
            aux(cols, d1, d2, queens, n, r + 1, res);
            queens.remove(r + "," + j);
            cols.remove(j);
            d1.remove(id1);
            d2.remove(id2);
        }
    }

    private static void buildRes(List<List<String>> res, Set<String> queens) {
        List<String> oneSolution = new LinkedList<String>();
        for (int i = 0; i < queens.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < queens.size(); j++) {
                sb.append(queens.contains(i + "," + j) ? 'Q' : '.');
            }
            oneSolution.add(sb.toString());
        }
        res.add(oneSolution);
    }
}
