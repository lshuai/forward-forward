package snapchat;
import java.util.*;
import java.util.stream.*;

class Cell {
    public int x; 
    public int y; 
    public int dist;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.dist = Integer.MAX_VALUE;
    }

    public Cell(int x, int y, int dist) {
        this(x, y);
        this.dist = dist;
    }

    public List<Cell> getNeighbors(int[][] map) {
        List<Cell> neighbors = new ArrayList<Cell>();
        if (this.x + 1 < map.length && map[this.x + 1][this.y] != 1)
            neighbors.add(new Cell(this.x + 1, this.y));
        if (this.x - 1 >= 0 && map[this.x - 1][this.y] != 1)
            neighbors.add(new Cell(this.x - 1, this.y));
        if (this.y + 1 < map[0].length && map[this.x][this.y + 1] != 1)
            neighbors.add(new Cell(this.x, this.y + 1));
        if (this.y - 1 >= 0 && map[this.x][this.y - 1] != 1)
            neighbors.add(new Cell(this.x, this.y - 1));

        return neighbors;
    }

    @Override
    public boolean equals(Object cell) {
        Cell c = (Cell) cell;
        return c.x == this.x && c.y == this.y;
    }
}

public class WallCostMin {
    public static void main(String[] args) {
        int[][] map = new int[][] {
            {0,1,1,0,0,0}, 
            {1,1,0,0,1,0}, 
            {1,1,0,0,1,0}, 
            {0,1,0,0,0,0}, 
            {0,1,0,1,1,1}};
        System.out.println(map[0].length);
        System.out.println(new WallCostMin().dfs(map, new Cell(0, 5), new Cell(0, 3)));
        System.out.println(new WallCostMin().dijkstra(map, new Cell(0, 5), new Cell(0, 3)));
    }
    public int dijkstra(int[][] map, Cell start, Cell end) {
        // init
        Set<Cell> visited = new HashSet<Cell>();
        Queue<Cell> unvisited = new PriorityQueue<Cell>((a, b) -> a.dist < b.dist ? -1 : (a.dist > b.dist ? 1 : 0));
        start.dist = 0;
        unvisited.offer(start);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                Cell c = new Cell(i, j);
                if (!c.equals(start))
                    unvisited.offer(c); 
            }
        }

        // process
        while (!unvisited.isEmpty()) {
            Cell cur = unvisited.poll(); 
            visited.add(cur);
            if (cur.equals(end))
                return cur.dist;
            if (cur.dist == Integer.MAX_VALUE)
                return -1;
            for (Cell n: cur.getNeighbors(map)) {
                if (visited.contains(n)) continue;
                int startToN = cur.dist + 1;
                if (startToN < n.dist) {
                    unvisited.remove(n);
                    n.dist = startToN;
                    unvisited.offer(n);
                }
            }
        }
        return -1;
    }

    public int dfs(int[][] map, Cell start, Cell end) {
        if (map == null || map[0] == null)
            return Integer.MAX_VALUE;
    
        int[] minCost = new int[] {Integer.MAX_VALUE};    
        dfsAux(map, start, end, 0, minCost);  
        return minCost[0];
    }

    private static void dfsAux(int[][] map, Cell cur, Cell end, int cost, int[] minCost) {
        if (cur.x == end.x && cur.y == end.y) {
            minCost[0] = Math.min(minCost[0], cost);
            return;
        } 
        if (cur.x >= map.length
            || cur.x < 0
            || cur.y < 0
            || cur.y >= map[0].length 
            || map[cur.x][cur.y] == 1 
            || map[cur.x][cur.y] == -1)
            return;
        
        int curValue = map[cur.x][cur.y];          
        map[cur.x][cur.y] = -1;
        dfsAux(map, new Cell(cur.x + 1, cur.y), end, cost + 1, minCost);
        dfsAux(map, new Cell(cur.x - 1, cur.y), end, cost + 1, minCost);
        dfsAux(map, new Cell(cur.x, cur.y + 1), end, cost + 1, minCost);
        dfsAux(map, new Cell(cur.x, cur.y - 1), end, cost + 1, minCost);
        map[cur.x][cur.y] = curValue;
    }
}

