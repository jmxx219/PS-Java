package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커_9465 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] score;

    private static int solve() {
        int[][] dp = new int[N + 1][3];

        for(int i = 1; i <= N; i++) {
            dp[i][0] = score[i][0] + Math.max(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = score[i][1] + Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }
        int res = Math.max(dp[N][0], dp[N][1]);
        res = Math.max(res,  dp[N][2]);
        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            score = new int[N + 1][2];
            for(int j = 0; j < 2; j++) {
                st = new StringTokenizer(br.readLine());
                for(int i = 1; i <= N; i++) {
                    score[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println(solve());
        }
    }

}
