package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Alphabet_1987 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int R;
    private static int C;
    private static char[][] board;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};
    private static int res = 1;


    public static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }

    private static void solve(int y, int x, int depth, boolean[] alpha) {
        if(res < depth) res = depth;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(!isRange(ny, nx)) continue;

            if(!alpha[board[ny][nx] - 'A']) {
                alpha[board[ny][nx] - 'A'] = true;
                solve(ny, nx, depth + 1, alpha);
                alpha[board[ny][nx] - 'A'] = false;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }
        boolean[] alpha = new boolean[26];
        alpha[board[0][0] - 'A'] = true;
        solve(0, 0, 1, alpha);
        System.out.println(res);
    }
}
