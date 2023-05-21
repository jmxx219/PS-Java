package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Coin_9084 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int N;
    private static int M;
    private static int[] coins;

    private static int solve() {
        int[] dp =  new int[M + 1];

        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = coins[i]; j <= M; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }

        return dp[M];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());

            System.out.println(solve());
        }
    }
}
