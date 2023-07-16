package BOJ.CodePlus.Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Monkey_1600 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int K;
    private static int W;
    private static int H;
    private static int[][] board;
    private static int[][] near = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int[][] knight = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    private static class Point {
        int y, x;
        int dist;
        int k;

        public Point(int y, int x, int dist, int k) {
            this.y = y;
            this.x = x;
            this.dist = dist;
            this.k = k;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < H && 0 <= x && x < W;
    }


    private static int solve() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[H][W][K + 1];

        queue.add(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Point here = queue.poll();

            if(here.y == H - 1 && here.x == W - 1) return here.dist;

            if(here.k < K) {
                for (int i = 0; i < 8; i++) {
                    int ny = here.y + knight[i][0];
                    int nx = here.x + knight[i][1];

                    if(!isRange(ny, nx) || board[ny][nx] == 1) continue;
                    if(!visited[ny][nx][here.k + 1]) {
                        visited[ny][nx][here.k + 1] = true;
                        queue.add(new Point(ny, nx, here.dist + 1, here.k + 1));
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int ny = here.y + near[i][0];
                int nx = here.x + near[i][1];

                if(!isRange(ny, nx) || board[ny][nx] == 1) continue;
                if(!visited[ny][nx][here.k]) {
                    visited[ny][nx][here.k] = true;
                    queue.add(new Point(ny, nx, here.dist + 1, here.k));
                }
            }

        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }
}
