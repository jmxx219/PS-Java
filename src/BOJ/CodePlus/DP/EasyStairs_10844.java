package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EasyStairs_10844 {
    private static final int MOD = 1_000_000_000;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static long[][] memo;

    public static int stairs_tb() { // 테뷸레이션
        long dp[][] = new long[N + 1][10]; // 길이가 N인 숫자에서 마지막 숫자가 0~9 사이인 계단 수
        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;

        for(int i = 1; i <= N; i++) {
            for(int j = 0; j <= 9; j++) {
                if(j - 1 >= 0) { // 1 ~ 9
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if(j + 1 <= 9){ // 0 ~ 8
                    dp[i][j] += dp[i - 1][j + 1];
                }
                dp[i][j] %= MOD;
            }
        }

        long res = 0;
        for(long cnt : dp[N]) {
            res += cnt;
        }
        res %= MOD;

        return (int) res;
    }

    public static long stairs_memo(int len, int num) {
        if(len == 1) {
            if(num == 0) {
                return 0;
            }
            return 1;
        }
        if(memo[len][num] != -1) {
            return memo[len][num];
        }

        long res = 0L;
        if(num - 1 >= 0) {
            res = (res + stairs_memo(len - 1, num - 1)) % MOD;
        }
        if(num + 1 <= 9) {
            res = (res + stairs_memo(len - 1, num + 1)) % MOD;
        }
        memo[len][num] = res % MOD;

        return memo[len][num];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

//        System.out.println(stairs_tb());

        memo = new long[N + 1][10];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(memo[i], -1);
        }

        long res = 0L;
        for(int i = 0; i <= 9; i++) {
            res = (res + stairs_memo(N, i)) % MOD;
        }
        System.out.println(res);

    }
}
