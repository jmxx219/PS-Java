package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SquareNumberSum_1699 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;

    public static int sumOfSquares() {
        int[] dp = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            for(int j = 2; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[N];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        System.out.println(sumOfSquares());
    }
}
