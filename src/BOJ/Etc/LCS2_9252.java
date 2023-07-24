package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS2_9252 {

    private static BufferedReader br;
    private static char[] s1;
    private static char[] s2;
    private static int[][] dp;
    private static int N;
    private static int M;

    private static int solve() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if(s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[N][M];
    }

    private static String findLcs() {
        StringBuilder sb = new StringBuilder();

        int i = N;
        int j = M;
        while (dp[i][j] != 0) {
            if(dp[i][j] == dp[i - 1][j]) i -= 1;
            else if(dp[i][j] == dp[i][j - 1]) j -= 1;
            else {
                sb.append(s1[i - 1]);
                i -= 1;
                j -= 1;
            }
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        s1 = br.readLine().toCharArray();
        s2 = br.readLine().toCharArray();

        N = s1.length;
        M = s2.length;

        dp = new int[N + 1][M + 1];

        System.out.println(solve());
        System.out.println(findLcs());
    }
}
