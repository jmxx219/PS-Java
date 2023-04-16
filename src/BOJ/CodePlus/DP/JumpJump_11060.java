package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JumpJump_11060 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] maze;

    private static int solve() {
        int[] dp = new int[N];
        Arrays.fill(dp, -1);

        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            if(dp[i] == -1) continue;
            for (int j = i + 1; j <= i + maze[i] && j < N; j++) {
                if(dp[j] == -1 || dp[j] > dp[i] + 1) dp[j] = dp[i] + 1;
            }
        }

        return dp[N - 1];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        maze = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            maze[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }
}
