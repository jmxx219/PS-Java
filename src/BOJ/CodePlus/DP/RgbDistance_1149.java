package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RgbDistance_1149 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] house;

    public static int solve() {
        int[][] dp = new int[N + 1][3]; // RED, GREEN, BLUE

        dp[1][0] = house[1][0];
        dp[1][1] = house[1][1];
        dp[1][2] = house[1][2];

        for(int i = 2; i <= N; i++) {
            dp[i][0] = house[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = house[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = house[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        int res = Math.min(dp[N][0], dp[N][1]);
        res = Math.min(res, dp[N][2]);
        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        house = new int[N + 1][3];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int color = 0; color < 3; color++) {
                house[i][color] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }
}
