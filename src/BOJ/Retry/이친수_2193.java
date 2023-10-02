package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이친수_2193 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;

    // dp[i][j] = i자리 이친수에서 j로 끝나는 개수
    private static long solve2() {
        long[][] dp = new long[N + 1][2];

        dp[1][0] = 0;
        dp[1][1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }

        return dp[N][0] + dp[N][1];
    }

    // 0으로 끝나는 경우 -> 앞에 0과 1 모두 가능, dp[i-1]
    // 1로 끝나는 경우 -> 앞에 0이 오고 그 앞에 0과 1 모두 가능, dp[i-2]
    // dp[i] = dp[i - 1] + dp[i - 2]
    private static long solve() {
        if(N <= 2) return 1;

        long[] dp = new long[N + 1];

        dp[1] = dp[2] = 1;
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[N];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        System.out.println(solve());
    }
}
