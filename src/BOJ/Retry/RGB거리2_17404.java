package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리2_17404 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static final int INF = 987654321;
    private static int[][] color;

    private static int solve() {
        int res = INF;
        int[][] dp = new int[N + 1][3];

        // R
        dp[2][0] = INF;
        dp[2][1] = color[2][1];
        dp[2][2] = color[2][2];
        for(int i = 3; i <= N; i++) {
            dp[i][0] = color[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = color[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = color[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        int red = color[1][0] + Math.min(dp[N][1], dp[N][2]);

        // G
        dp[2][0] = color[2][0];
        dp[2][1] = INF;
        dp[2][2] = color[2][2];
        for(int i = 3; i <= N; i++) {
            dp[i][0] = color[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = color[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = color[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        int green = color[1][1] + Math.min(dp[N][0], dp[N][2]);

        // B
        dp[2][0] = color[2][0];
        dp[2][1] = color[2][1];
        dp[2][2] = INF;
        for(int i = 3; i <= N; i++) {
            dp[i][0] = color[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = color[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = color[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        int blue = color[1][2] + Math.min(dp[N][0], dp[N][1]);


        res = Math.min(red, blue);
        res = Math.min(res, green);

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        color = new int[N + 1][3];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solve());
    }

}
