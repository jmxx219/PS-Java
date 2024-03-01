package BOJ.Etc;

import java.util.Arrays;
import java.util.Scanner;

public class Main_1463_1로만들기 {
    private static final int INF = 987654321;
    private static int N;

    public static int makeOne() {
        if(N == 1) return 0;

        int[] dp = new int[N + 1];
        Arrays.fill(dp, INF);
        
        dp[0] = dp[1] = 0;
        for(int i = 2; i <= N; i++) {
            if(i % 3 == 0)dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            dp[i] = Math.min(dp[i], dp[i - 1] + 1);
        }
        return dp[N];
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        System.out.println(makeOne());
    }
}
