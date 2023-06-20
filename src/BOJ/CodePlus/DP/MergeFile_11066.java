package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MergeFile_11066 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int K;
    private static int[] file;
    private static final int INF = 987654321;

    private static int solve() {
        int[] sum = new int[K];
        sum[0] = file[0];
        for (int i = 1; i < K; i++) {
            sum[i] = sum[i - 1] + file[i];
        }

        int[][] dp = new int[K][K]; // dp[i][j] = dp[i][k] + dp[k][j]

        for (int i = 0; i < K - 1; i++) {
            dp[i][i + 1] = file[i] + file[i + 1];
        }

        for (int gap = 2; gap < K; gap++) {
            for (int i = 0; i + gap < K; i++) {
                int j = i + gap;
                dp[i][j] = INF;

                for (int k = i; k < j; k++) {
                    int sumDist = (i == 0) ? sum[j] : sum[j] - sum[i - 1];
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sumDist);
                }
            }
        }

        return dp[0][K - 1];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            file = new int[K];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                file[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println(solve());
        }
    }
}
