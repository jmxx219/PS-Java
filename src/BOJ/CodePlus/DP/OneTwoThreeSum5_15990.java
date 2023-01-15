package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1, 2, 3 더하기 5
public class OneTwoThreeSum5_15990 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int[][] memo;
    private static long[][] dp;
    private static final int MOD = 1_000_000_009;

    public static void countOneTwoThree3() { // 테뷸레이션
        dp = new long[100001][4];
        dp[1][1] = dp[2][2] = dp[3][1] = dp[3][2] = dp[3][3] = 1;

        for(int i = 4; i < dp.length; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % MOD;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % MOD;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % MOD;
        }
    }

    public static void countOneTwoThree2() { // 테뷸레이션
        dp = new long[100001][4];
        for(int i = 1; i < dp.length; i++) {
            if(i - 1 >= 0) {
                dp[i][1] = dp[i - 1][2] + dp[i - 1][3];
                if(i == 1) {
                    dp[i][1] = 1;
                }
            }
            if(i - 2 >= 0) {
                dp[i][2] = dp[i - 2][1] + dp[i - 2][3];
                if(i == 2) {
                    dp[i][2] = 1;
                }
            }
            if(i - 3 >= 0) {
                dp[i][3] = dp[i - 3][1] + dp[i - 3][2];
                if(i == 3) {
                    dp[i][3] = 1;
                }
            }
            dp[i][1] %= MOD;
            dp[i][2] %= MOD;
            dp[i][3] %= MOD;
        }
    }

    public static int countOneTwoThree(int target, int prev) { // 메모이제이션
        if(target == 0) {
             return 1;
        }
        else if(target < 0) {
            return 0;
        }

        if(memo[target][prev] != -1) {
            return memo[target][prev];
        }

        int res = 0;
        for(int i = 1; i < 4; i++) {
            if(i != prev) {
                res = (res + (countOneTwoThree(target - i, i) % MOD)) % MOD;
            }
        }
        return memo[target][prev] = res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        countOneTwoThree2();

        memo = new int[100001][4];
        for(int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        T = Integer.parseInt(st.nextToken());
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
//            System.out.println(countOneTwoThree(N, 0));
            System.out.println((dp[N][1] + dp[N][2] + dp[N][3]) % MOD);
        }
    }
}
