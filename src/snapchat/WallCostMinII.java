package snapchat;
import java.util.*;

/*
 * 有墙找路的变形 1 是路 0 是墙， 让你从左边col的任意点走到右边col的任意点的最小step。走不到就返回-1
 * 注意起始点是任意左col的点 而不是左上 ， 也不是走到右下。 要考虑到中间如果有一个col全部都是0（墙）
 */

class WallCell {
    public int x;
    public int y;
    public int dist;
    public WallCell parent;
    
    public WallCell(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    public List<WallCell> getNeighbors() {
        return Arrays.asList(new WallCell(x + 1, y, dist + 1), 
            new WallCell(x - 1, y, dist + 1), new WallCell(x, y + 1, dist + 1), new WallCell(x, y - 1, dist + 1));
    }
}

public class WallCostMinII {
    public static void main(String[] args) {
        System.out.println(cost(new int[][] {
            {0,1,1,0,0,0}, 
            {1,1,0,0,1,0}, 
            {1,1,1,1,1,1}, 
            {0,1,0,0,0,0}, 
            {0,1,1,1,1,1}}));
    }
    public static int cost(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0)
            return -1;
        
        int r = map.length;
        int c = map[0].length;
        Queue<WallCell> queue = new LinkedList<WallCell>();
        boolean[][] visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            if (map[i][0] == 0)
                continue;
            queue.offer(new WallCell(i, 0, 0));
        }
        while (!queue.isEmpty()) {
            WallCell cur = queue.poll();
            if (visited[cur.x][cur.y])
                continue;
            if (cur.y == c - 1) {
                print(cur);
                return cur.dist;
            }
            visited[cur.x][cur.y] = true;
            for (WallCell cell: cur.getNeighbors()) {
                if (valid(cell, visited, map)) {
                    cell.parent = cur;
                    queue.offer(cell);
                }
            }
        }

        return -1;
    }
    
    public static void print(WallCell last) {
        WallCell cur = last;
        Deque<WallCell> path = new LinkedList<WallCell>();
        while (cur != null) {
            path.addFirst(cur);
            cur = cur.parent;
        }
        for (WallCell c: path) 
            System.out.print(c.x + "," + c.y + " ");
        System.out.println();
    }

    private static boolean valid(WallCell c, boolean[][] visited, int[][] map) {
        return c.x >= 0 && c.y >= 0 && c.x < visited.length 
            && c.y < visited[0].length && !visited[c.x][c.y] && map[c.x][c.y] == 1;
    }
}
