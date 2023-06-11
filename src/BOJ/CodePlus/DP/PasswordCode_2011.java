package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PasswordCode_2011 {
    private static BufferedReader br;
    private static String num;
    private static final int MOD = 1000000;

    private static boolean isAlphabet(int n) {
        return 1 <= n && n <= 26;
    }

    private static boolean isCode(int i) {
        int prev = num.charAt(i - 1) - '0';
        if(prev == 0) return false;

        int n = Integer.parseInt(num.substring(i - 1, i + 1));
        return isAlphabet(n);
    }

    private static long solve() {
        if(num.length() == 0 || num.charAt(0) == '0') return 0;

        long[] dp = new long[num.length()];

        dp[0] = 1;

        for (int i = 1; i < num.length(); i++) {
            int x = num.charAt(i) - '0';
            if(isAlphabet(x)) dp[i] = dp[i - 1] % MOD;

            if(isCode(i)) {
                if(i == 1) dp[i] += 1;
                else dp[i] += dp[i - 2] % MOD;
            }
        }

        return dp[num.length() - 1] % MOD;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        num = br.readLine();
        System.out.println(solve());
    }
}
