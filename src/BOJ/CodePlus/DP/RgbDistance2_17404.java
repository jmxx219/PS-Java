package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RgbDistance2_17404 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] house;
    private static final int INF = 1000 * 1000 + 1;

    public static int rgbColor(int firstColor) {
        int[][] dp = new int[N + 1][3]; // RED, GREEN, BLUE

        for (int c = 0; c <= 2; c++) {
            if (c == firstColor) {
                dp[1][c] = house[1][c];
            } else {
                dp[1][c] = INF;
            }
        }

        for(int i = 2; i <= N; i++) {
            dp[i][0] = house[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = house[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = house[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        int res = INF;
        for (int c = 0; c <= 2; c++) {
            if (c == firstColor) continue;
            res = Math.min(res, dp[N][c]);
        }
        return res;
    }

    public static int solve() {
        int res = INF;
        for(int color = 0; color < 3; color++) { // 1번 집 색 고정
            res = Math.min(res, rgbColor(color));
        }
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
