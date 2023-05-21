package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FiboExtension_1788 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static final int MOD = 1_000_000_000;

    private static int solve(int X) {
        if(X <= 1) return X;

        int[] dp = new int[X + 1];

        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= X; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        return dp[X];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int X = Math.abs(N);

        int symbol = 1;
        if(N == 0) symbol = 0;
        else if(N < 0 && X % 2 == 0) symbol = -1;

        System.out.println(symbol);
        System.out.println(solve(X));
    }
}
