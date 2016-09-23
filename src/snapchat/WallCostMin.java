import java.util.*;
import java.util.stream.*;

class Cell {
    public int x; 
    public int y; 

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class WallCostMin {
    public static void main(String[] args) {

    }

    public int minCost(int[][] map, Cell start, Cell end) {
        if (map == null || map[0] == null)
            return Integer.MAX_VALUE;
    
        int[] minCost = new int[] {Integer.MAX_VALUE};    
        minCostAux(map, start, end, 0, minCost);  
        return minCost[0];
    }

    private static void minCostAux(int[][] map, Cell cur, Cell end, int cost, int[] minCost) {
        if (cur.x >= map.length
            || cur.y >= map[0].length 
            || map[cur.x][cur.y] == 1 
            || map[cur.x][cur.y] == -1)
            return;
        if (cur.x == end.x && cur.y == end.y) {
            minCost[0] = Math.min(minCost[0], cost);
            return;
        } 
        
        int curValue = map[cur.x][cur.y];          
        map[cur.x][cur.y] = -1;
        minCostAux(map, new Cell(cur.x + 1, cur.y), end, cost + 1, minCost);
        minCostAux(map, new Cell(cur.x - 1, cur.y), end, cost + 1, minCost);
        minCostAux(map, new Cell(cur.x, cur.y - 1), end, cost + 1, minCost);
        minCostAux(map, new Cell(cur.x, cur.y + 1), end, cost + 1, minCost);
        map[cur.x][cur.y] = curValue;
    }
}
