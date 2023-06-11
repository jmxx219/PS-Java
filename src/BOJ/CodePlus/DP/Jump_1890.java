package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jump_1890 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] board;
    private static int[][] memo;

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static long solve() {
        long[][] dp = new long[N][N];

        dp[0][0] = 1;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(dp[y][x] == 0) continue;
                if(y == N - 1 && x == N - 1) return dp[N - 1][N - 1];
                int jump = board[y][x];
                if(isRange(y + jump, x)) {
                    dp[y + jump][x] += dp[y][x];
                }
                if(isRange(y, x + jump)) {
                    dp[y][x + jump] += dp[y][x];
                }
            }
        }

        return dp[N - 1][N - 1];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }
}
