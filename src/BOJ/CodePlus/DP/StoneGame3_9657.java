package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StoneGame3_9657 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;

    private static boolean solve() {
        boolean[] dp = new boolean[1001]; // true: 상근이가 이길 떄

        dp[1] = true;
        dp[2] = false;
        dp[3] = true;
        dp[4] = true;
        for (int i = 5; i <= N; i++) {
            if (!dp[i - 1]|| !dp[i - 3] || !dp[i - 4]){
                dp[i] = true;
            }
        }

        return dp[N];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        if (solve()) System.out.println("SK");
        else System.out.println("CY");
    }
}
