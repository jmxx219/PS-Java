package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이_붙이기_17136 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static final int N = 10;
    private static int[][] paper;
    private static int res;

    private static boolean isOk(int y, int x, int len) {
        if(y + len >= N || x + len >= N) return false;

        for (int i = y; i <= y + len; i++) {
            for (int j = x; j <= x + len; j++) {
                if(paper[i][j] == 0) return false;
            }
        }
        return true;
    }

    private static void toggle(int y, int x, int len, int tmp) {
        for (int i = y; i <= y + len; i++) {
            for (int j = x; j <= x + len; j++) {
                paper[i][j] = tmp;
            }
        }
    }

    private static void solve(int y, int x, int cnt, int[] color) {
        if(y == N) {
            res = Math.min(res, cnt);
            return;
        }

        if(x == N) {
            solve(y + 1, 0, cnt, color);
            return;
        }

        if(paper[y][x] == 1) {
            for (int i = 4; i >= 0; i--) {
                if (!isOk(y, x, i) || color[i] <= 0) continue;
                toggle(y, x, i, 0);
                color[i] -= 1;
                solve(y, x + 1, cnt + 1, color);
                toggle(y, x, i, 1);
                color[i] += 1;
            }
        }
        else solve(y, x + 1, cnt, color);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = Integer.MAX_VALUE;
        solve(0, 0, 0, new int[] {5, 5, 5, 5, 5});
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }
}
