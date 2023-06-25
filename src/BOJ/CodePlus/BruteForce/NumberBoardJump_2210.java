package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class NumberBoardJump_2210 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static final int N = 5;
    private static String[][] board;
    private static int[] dy = {1, -1, 0, 0};
    private static int[] dx = {0, 0, 1, -1};
    private static Set<String> res;

    private static boolean isRange(int y, int x) {
        return  0 <= y && y < N && 0 <= x && x < N;
    }

    private static void search(int y, int x, String select) {
        if(select.length() == N + 1) {
            res.add(select);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(!isRange(ny, nx)) continue;
            search(ny, nx, select + board[ny][nx]);
        }
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                search(i, j, board[i][j]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        board = new String[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().split(" ");
        }

        res = new HashSet<>();
        solve();
        System.out.println(res.size());
    }
}
