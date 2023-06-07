package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TriangularGraph_4883 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] graph;

    private static long solve() {
        long[][] dp = new long[N][3];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }

        dp[0][1] = graph[0][1];
        dp[0][2] = graph[0][2] + graph[0][1];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + graph[i][0];
            dp[i][1] = Math.min(
                    Math.min(dp[i - 1][0], dp[i][0]),
                    Math.min(dp[i - 1][1], dp[i - 1][2])
            ) + graph[i][1];

            dp[i][2] = Math.min(
                    dp[i][1], Math.min(dp[i - 1][1], dp[i - 1][2])
            ) + graph[i][2];
        }

        return dp[N - 1][1];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int t = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N == 0) break;

            graph = new int[N][3];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            long res = solve();
            System.out.println(t + ". " + res);
            t += 1;
        }

    }
}
