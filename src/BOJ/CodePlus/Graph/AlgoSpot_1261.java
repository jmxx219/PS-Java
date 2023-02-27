package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AlgoSpot_1261 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N; // 세로
    private static int M; // 가로
    private static int[][] maze;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};

    public static class Point {
        public int y;
        public int x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        int[][] dist = new int[N][M];
        for(int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        queue.add(new Point(0, 0));
        dist[0][0] = 0;

        while (!queue.isEmpty()) {
            Point here = queue.poll();
            for(int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if(!isRange(ny, nx)) continue;

                int nextDist = dist[here.y][here.x];
                if(maze[ny][nx] == 1) { // 벽
                    nextDist += 1;
                }

                if(dist[ny][nx] == -1 || nextDist < dist[ny][nx]) {
                    queue.add(new Point(ny, nx));
                    dist[ny][nx] = nextDist;
                }
            }
        }

        return dist[N - 1][M - 1];
    }
}
