import java.util.*;

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9)
            return false;
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char cur = board[i][j];
                if (cur == '.') continue;
                String inRow = "inRow" + cur + i;
                String inCol = "inCol" + cur + j;
                String inCorner = "inCorner" + cur + i / 3 + j / 3;
                if (set.contains(inRow) || set.contains(inCol) || set.contains(inCorner))
                    return false;
                set.add(inRow);
                set.add(inCol);
                set.add(inCorner);
            }
        }
        return true;
    }
}
