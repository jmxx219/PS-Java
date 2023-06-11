package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ColorWheel_2482 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static final int MOD = 1_000_000_003;

    private static int solve() {
        if (K > N / 2) return 0;

        int[][] dp = new int[N + 1][K + 1]; // i개의 색상환 중 j개 뽑는 경우의 수

        for (int i = 1; i <= N; i++) {
            dp[i][1] = i;
        }

        for (int i = 4; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % MOD;
            }
        }

        return dp[N][K];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        System.out.println(solve());

    }
}
