package snapchat;
import java.util.*;
import java.util.stream.*;

// 1st next 2nd current
// 00 01 10 11
// 0, 1, 2, 3
public class GameOfLife {
   public static void main(String[] args) {
       int[][] board = new int[][]{{1,1}, {1,0}};
       new GameOfLife().gameOfLife(board);
       System.out.println("??");
   }
    
   public void gameOfLife(int[][] board) {
       int m = board.length, n = board[0].length;
       for (int i = 0; i < m; i++) {
           for (int j = 0; j < n; j++) {
               int lives = getLives(board, m, n, i, j);
               if ((board[i][j] & 1) == 1) 
                   board[i][j] = (lives == 2 || lives == 3) ? 3 : 1;
               else
                   board[i][j] = lives == 3 ? 2 : 0;
           }
       }
       for (int i = 0; i < m; i++) {
           for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] >> 1;
           }
       }
   } 

   private static int getLives(int[][] board, int m, int n, int r, int c) {
       int count = 0;
       for (int i = r - 1; i <= r + 1; i++) {
           for (int j = c - 1; j <= c + 1; j++) {
               if (i < m && i >= 0 && j < n && j >= 0 && (board[i][j] & 1) == 1 && !(i == r && j == c))
                   count++;
           }
       }
       return count;
   }
}
