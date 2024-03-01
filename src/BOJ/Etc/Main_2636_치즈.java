package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2636_치즈 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N; // 세로
    private static int M; // 가로
    private static int[][] board;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};
    private static boolean[][] visited;
    private static int cheese = 0;

    public static class Point {
        public int y, x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static boolean isRange(int y, int x) {
        return  0 <= y && y < N && 0 <= x && x < M;
    }

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Point here = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if(!isRange(ny, nx)) continue;

                if(!visited[ny][nx]) {
                    visited[ny][nx] = true;
                    if(board[ny][nx] == 0) {
                        queue.add(new Point(ny, nx));
                    }
                    else {
                        cheese--;
                        board[ny][nx] = 0;
                    }
                }
            }
        }
    }

    public static void solve() {
        int time = 0;
        int cnt = 0;

        while (cheese > 0) {
            cnt = cheese;
            time += 1;
            visited = new boolean[N][M];
            bfs();
        }

        System.out.println(time);
        System.out.println(cnt);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 1) cheese += 1;
            }
        }
        solve();
    }
}
