package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OneTwoThreeSum4_15989 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static final int MAX = 10_000;
    private static final int[] nums = {1, 2, 3};

    private static int[] solve() {
        int[] dp = new int[MAX + 1];

        dp[0] = 1;
        for(int num : nums) {
            for (int i = 1; i <= MAX; i++) {
                if(i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        int[] dp = solve();

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            System.out.println(dp[n]);
        }

    }
}
