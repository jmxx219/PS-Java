package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumDecomposition_2225 {
    private static final int MOD = 1_000_000_000;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;

    public static int solve() {
        int[][] dp = new int[N + 1][K + 1];
        int res = 0;

        for(int i = 1; i <= N; i++) {
            dp[i][1] = 1;
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= K; j++) {
                for(int k = 0; k < i; k++) {
                    dp[i][j] = (dp[i][j] + dp[i-k][j-1]) % MOD;
                }
            }
        }

        for(int j = 1; j <= K; j++) {
            res = (res + dp[N][j]) % MOD;
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(solve());
    }
}
