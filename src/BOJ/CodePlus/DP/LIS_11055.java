package BOJ.CodePlus.DP;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

// 가장 큰 증가 부분 수열
public class LIS_11055 {
    private static final int INF = -987654321;
    private static int N;
    private static int[] nums;
    private static int[] memo;

    public static int lis() { // 테뷸레이션
        int[] dp = new int[N];
        for(int i = 0; i < N; i++) {
            dp[i] = nums[i];
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], nums[i] + dp[j]);
                }
            }
        }
        System.out.println(Arrays.stream(dp).mapToObj(Objects::toString).collect(Collectors.joining(" ")));
        return Arrays.stream(dp).max().getAsInt();
    }

    public static int lis2(int here) { // 메모이제이션
        if(here >= N) {
            return 0;
        }
        if(memo[here + 1] != 0) {
            return memo[here + 1];
        }

        int res  = 0;
        for(int next = here + 1; next < N; next++){
            if(here == -1 || nums[next] > nums[here]){
                res = Math.max(res, lis2(next));
            }
        }
        int init = (here == -1) ? 0 : nums[here];
        memo[here+1] = res + init;
        return memo[here+1];
    }
    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        N = in.nextInt();
        nums = new int[N];
        memo = new int[N + 1];
        for(int i = 0; i < N; i++){
            nums[i] = in.nextInt();
        }
//        System.out.println(lis());
        System.out.println(lis2(-1));
//        System.out.println(Arrays.stream(memo).mapToObj(Objects::toString).collect(Collectors.joining(" ")));
    }
}
