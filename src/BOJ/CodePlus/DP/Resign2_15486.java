package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Resign2_15486 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] time;
    private static int[] pay;

    public static int solve() {
        int[] dp = new int[N + 50]; // dp[i]: i일까지 상담했을 때 최대 수익

        for (int i = 0; i < N; i++) {
            dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + pay[i]);
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }

        return dp[N];
    }

//    public static int solve2() {
//        int[] dp = new int[N + 1];
//
//        int res = -1;
//        for (int i = 0; i <= N; i++) {
//            if(res < dp[i]) res = dp[i];
//            int next = i + time[i];
//            if(next <= N) dp[next] = Math.max(dp[next], res + pay[i]);
//        }
//
//        return res;
//    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        time = new int[N];
        pay = new int[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken()); // 기간
            pay[i] = Integer.parseInt(st.nextToken()); // 금액
        }

        System.out.println(solve());
    }
}
