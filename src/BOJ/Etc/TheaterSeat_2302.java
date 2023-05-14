package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TheaterSeat_2302 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N; // 좌석 개수
    private static int M; // 고정석 개수
    private static int[] fixedSeat;
    private static int MAX = 40;

    public static int solve() {
        if(N == M) return 1;

        // 점화식: dp[n] = dp[n-1] + dp[n-2]
        int[] dp = new int[MAX + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < MAX + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }


        int res = 1;
        int prev = 0;
        for (int i = 0; i < M; i++) {
            res *= dp[fixedSeat[i] - prev - 1];
            prev = fixedSeat[i];
        }
        res *= dp[N - prev];

        return res;
    }


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        fixedSeat = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            fixedSeat[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }
}
