package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 합분해_2225 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static int MOD = 1_000_000_000;

    private static long solve() {
        long[][] dp = new long[N + 1][K + 1];

        dp[0][0] = 1;
        for(int j = 1; j <= K; j++) {
            for(int i = 0; i <= N; i++) {
                for(int k = 0; k <= i; k++) {
                    dp[i][j] += dp[i - k][j - 1];
                    dp[i][j] %= MOD;
                }
            }
        }

        return dp[N][K];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(solve());
    }
}
