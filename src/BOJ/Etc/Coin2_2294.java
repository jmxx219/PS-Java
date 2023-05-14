package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Coin2_2294 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static int[] nums;
    private static int INF = 10001;

    public static int solve() {
        int[] dp = new int[K + 1];
        Arrays.fill(dp, INF);

        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = nums[i]; j < K + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j - nums[i]] + 1);
            }
        }

        return dp[K] == INF ? -1 : dp[K];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }
}
