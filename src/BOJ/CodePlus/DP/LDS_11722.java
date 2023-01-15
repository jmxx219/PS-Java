package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 가장 긴 감소하는 부분 수열
public class LDS_11722 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] nums;
    private static int[] memo;

    public static int lds_memo(int start){ // 메모이제이션
        if(memo[start] != -1) {
            return memo[start];
        }

        int res = 1;
        for(int j = start + 1; j < N; j++) {
            if(nums[j] < nums[start]) {
                res = Math.max(res, lds_memo(j) + 1);
            }
        }

        return memo[start] = res;
    }

    public static int lds_tb() { // 테뷸레이션
        int[] dp = new int[N];

        for(int i = 0; i < N; i++) {
            dp[i] = 1;
            for(int j = 0; j <= i; j++) {
                if(nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            System.out.println(Arrays.toString(dp));
        }

//        Arrays.fill(dp, 1);
//        for(int i = N - 1; i >= 0; i--) {
//            for(int j = i - 1; j >= 0; j--) {
//                if(nums[i] < nums[j]) {
//                    dp[j] = Math.max(dp[j], dp[i] + 1);
//                }
//            }
//        }

        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nums = new int[N];

        memo = new int[N];
        Arrays.fill(memo, -1);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
//        System.out.println(lds_tb());

        int res = 0;
        for(int i = 0; i < N; i++) {
            res = Math.max(res, lds_memo(i));
        }
        System.out.println(res);
    }
}
