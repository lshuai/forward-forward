package review.snapchat;

// Curent Next
public class GameOfLife {
    public static void main(String[] args) {
        GameOfLife g = new GameOfLife();
        g.gameOfLife(new int[][] {{1,1}});
    }
    
    public void gameOfLife(int[][] board) {
        if (board == null )
            return;
        int m = board.length;        
        int n = board[0].length;
        if (m == 0 || n == 0)
            return;
        
        // change state to current dead
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) 
                board[i][j] = board[i][j] == 0 ? 0 : 2;
        }
        // compute next state
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = getLives(board, m, n, i, j);
                if (board[i][j] == 0 || board[i][j] == 1) 
                    board[i][j] = lives == 3 ? 1 : 0;
                else 
                    board[i][j] = lives == 3 || lives == 2 ? 3 : 2;
            }
        }
        // change state to next
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) 
                board[i][j] = board[i][j] == 0 || board[i][j] == 2 ? 0 : 1;
        }
    }

    private int getLives(int[][] board, int m, int n, int r, int c) {
        int lives = 0;
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] == 0 || board[i][j] == 1)
                    continue;
                lives++;
            }
        }
        return lives;
    }
}
