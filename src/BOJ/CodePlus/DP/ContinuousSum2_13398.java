package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ContinuousSum2_13398 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] nums;

    public static int continuousSum_tb() {
        int[] dp_end = new int[N];
        int[] dp_start = new int[N];
        for(int i = 0; i < N; i++) {
            dp_end[i] = nums[i];
            dp_start[i] = nums[i];
        }

        for(int i = 1; i < N; i++) { // i번째 수로 끝나는 큰 연속합
            dp_end[i] = Math.max(dp_end[i], dp_end[i - 1] + nums[i]);
        }
        for(int i = N - 2; i > 0; i--) { // // i번째 수로 시작하는 큰 연속합
            dp_start[i] = Math.max(dp_start[i], dp_start[i + 1] + nums[i]);
        }

        int res = Arrays.stream(dp_end).max().getAsInt();

        for(int i = 1; i < N - 1; i++) {
            res = Math.max(res, dp_end[i - 1] + dp_start[i + 1]);
        }

        return res;
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
