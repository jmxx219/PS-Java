package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 알고스팟_1261 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] maze;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};

    public static class Point implements Comparable<Point> {
        int y, x;
        int cost;

        public Point(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.cost - o.cost;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static int bfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        dist[0][0] = 0;
        pq.offer(new Point(0, 0, 0));

        while (!pq.isEmpty()) {
            Point here = pq.poll();

            if(dist[here.y][here.x] < here.cost) continue;
            if(here.y == N - 1 && here.x == M - 1) break;

            for (int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if(!isRange(ny, nx)) continue;

                int nextDist = dist[here.y][here.x];
                if(maze[ny][nx] == 1) nextDist += 1;

                if(dist[ny][nx] == -1 || nextDist < dist[ny][nx]) {
                    dist[ny][nx] = nextDist;
                    pq.offer(new Point(ny, nx, nextDist));
                }
            }
        }

        return dist[N - 1][M - 1];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        for (int i = 0; i < N; i++) {
            maze[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(bfs());
    }
}
