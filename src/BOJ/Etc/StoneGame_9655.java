package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StoneGame_9655 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;

    private static boolean solve() {
        int[] dp = new int[N + 1]; // 상근: 0, 칭영: 1

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 1;
        for (int i = 4; i <= N; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 3]) + 1;
        }

        return dp[N] % 2 == 1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        if (solve()) System.out.println("SK");
        else System.out.println("CY");
    }
}
