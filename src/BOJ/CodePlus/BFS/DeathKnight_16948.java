package BOJ.CodePlus.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DeathKnight_16948 {
    public static class Point {
        public int y;
        public int x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static Point start;
    private static Point end;
    private static final int[] dy = {-2, -2, 0, 0, 2, 2};
    private static final int[] dx = {-1, 1, -2, 2, -1, 1};

    private static boolean isRange(int y, int x) {
        return  0 <= y && y < N && 0 <= x && x < N;
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        int[][] dist = new int[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }
        queue.add(new Point(start.y, start.x));
        dist[start.y][start.x] = 0;

        while (!queue.isEmpty()) {
            Point here = queue.poll();
            for(int i = 0; i < dy.length; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if(!isRange(ny, nx)) continue;

                if(dist[ny][nx] == -1) {
                    queue.add(new Point(ny, nx));
                    dist[ny][nx] = dist[here.y][here.x] + 1;
                }
            }
        }

        return dist[end.y][end.x];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        System.out.println(bfs());
    }
}
