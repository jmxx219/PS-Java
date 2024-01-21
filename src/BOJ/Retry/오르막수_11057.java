package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 오르막수_11057 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static final int MOD = 10_007;

    private static int solve() {
        int[][] dp = new int[N + 1][11];
        Arrays.fill(dp[1], 1);
        for(int i = 2; i <= N; i++) {
            for(int j = 0; j < 10; j++) {
                for(int k = 0; k <= j; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
                }
            }
        }
        int cnt = 0;
        for(int j = 0 ; j < 10; j++) {
            cnt = (cnt + dp[N][j]) % MOD;
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        System.out.println(solve());
    }

}
