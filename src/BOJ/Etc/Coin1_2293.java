package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Coin1_2293 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static int[] coins;

    public static int solve() {
        int[] dp = new int[K + 1];

        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = coins[i]; j <= K; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        
        return dp[K];
    }


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(solve());
    }

}
