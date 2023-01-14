package BOJ.CodePlus.DP;

import java.util.Arrays;
import java.util.Scanner;

public class BuyCard_11052 {
    private static final int INF = -987654321;
    private static int N;
    private static int[] PS;
    private static int[] memo;

    public static int buyCards2() { // 테뷸레이션
        int[] dp = new int[N + 1];
        for(int n = 1; n <= N; n++) {
            for(int i = 1; i <= n; i++) {
                dp[n] = Math.max(dp[n], PS[i] + dp[n - i]);
            }
        }
        return dp[N];
    }

    public static int buyCards(int count) { // 메모이제이션
        if(count == 0) {
            return 0;
        }
        else if(count < 0) {
            return INF;
        }
        if(memo[count] != -1) {
            return memo[count];
        }

        int res = 0;
        for(int i = 1; i <= N; i++) {
            res = Math.max(res, PS[i] + buyCards(count - i));
        }
        return memo[count] = res;
    }

    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        N = in.nextInt();
        PS = new int[N + 1];
        memo = new int[N + 1];
        Arrays.fill(memo, -1);
        for(int i = 1; i <= N; i++) {
            PS[i] = in.nextInt();
        }
//        System.out.println(buyCards(N));
//        System.out.println(Arrays.stream(memo).mapToObj(Objects::toString).collect(Collectors.joining(" ")));
        System.out.println(buyCards2());
    }
}
