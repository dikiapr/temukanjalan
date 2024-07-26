import java.util.*;

class Point {
    int x, y, dist;
    String path;

    Point(int x, int y, int dist, String path) {
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.path = path;
    }
}

public class JalanTercepat {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static String[] directions = {"kanan", "kiri", "bawah", "atas"};

    public static void main(String[] args) {
        char[][] map = {
                {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','*','#'},
                {'#','#','#','#','#','#','#','#','#','#','#','#',' ','#','#','#','#','#','#','#','#','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ','#','#','#','#','#','#','#',' ','#','#','#','#','#','#','#','#','#','#','#','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','^',' ',' ',' ','#'},
                {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
        };

        findShortestPath(map);
    }

    static void findShortestPath(char[][] map) {
        int n = map.length;
        int m = map[0].length;
        boolean[][] visited = new boolean[n][m];
        Point start = null, end = null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '^') {
                    start = new Point(i, j, 0, "");
                } else if (map[i][j] == '*') {
                    end = new Point(i, j, 0, "");
                }
            }
        }

        if (start == null || end == null) {
            System.out.println("tidak ada jalan");
            return;
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            if (p.x == end.x && p.y == end.y) {
                System.out.println(p.path);
                System.out.println(p.dist + " langkah");
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (isValid(nx, ny, n, m, map, visited)) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny, p.dist + 1, p.path + (p.dist == 0 ? "" : ", ") + "1 " + directions[i]));
                }
            }
        }

        System.out.println("tidak ada jalan");
    }

    static boolean isValid(int x, int y, int n, int m, char[][] map, boolean[][] visited) {
        return x >= 0 && y >= 0 && x < n && y < m && map[x][y] != '#' && !visited[x][y];
    }
}
