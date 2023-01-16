package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ContinuousSum_1912 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] nums;

    public static int continuousSum_tb() {
        int[] dp = new int[N];
        for(int i = 0; i < N; i++) {
            dp[i] = nums[i];
        }

        for(int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1] + nums[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(continuousSum_tb());
    }
}
