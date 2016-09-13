package pinterest;
import java.util.*;

public class MatrixDiag {
    public static void main(String[] args) {
        char[][] matrix = new char[][]  {
                                {'a','b','c','d'},
                                {'d','e','f','g'},
                                {'h','i','j','k'}};
    }

    public void print(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '$') continue;
                int row = i;
                int col = j;
                while (row < m && col < n) {
                    System.out.print(matrix[row][col] + " ");
                    matrix[row++][col++] = '$';
                }
                System.out.println();
            }
        }
    }
}