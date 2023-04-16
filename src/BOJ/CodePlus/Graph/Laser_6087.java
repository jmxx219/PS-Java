package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Laser_6087 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int W;
    private static int H;
    private static char[][] map;
    private static Point start = null;
    private static Point end = null;
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};

    public static class Point {
        public int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < H && 0 <= x && x < W;
    }

    private static int solve() {
        Queue<Point> queue = new LinkedList<>();
        int[][] dist = new int[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(dist[i], -1);
        }

        queue.add(start);
        dist[start.y][start.x] = 0;

        while (!queue.isEmpty()) {
            Point here = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];

                while (isRange(ny, nx)) {
                    if(map[ny][nx] == '*') break;
                    if(dist[ny][nx] == -1) {
                        dist[ny][nx] = dist[here.y][here.x] + 1;
                        queue.add(new Point(ny, nx));
                    }
                    ny += dy[i];
                    nx += dx[i];
                }
            }
        }

        return dist[end.y][end.x] - 1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                if(map[i][j] == 'C') {
                    if(start == null) start = new Point(i, j);
                    else end = new Point(i, j);
                }
            }
        }

        System.out.println(solve());
    }
}
