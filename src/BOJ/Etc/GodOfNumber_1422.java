package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GodOfNumber_1422 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int K;
    private static int N;
    private static String[] nums;
    private static int maxN = -1;

    public static String solve() {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(nums, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        boolean isPlus = false;

        for(String n : nums) {
            sb.append(n);
            if(!isPlus && maxN == Integer.parseInt(n)) {
                isPlus = true;
                for(int i = 0; i < (N - K); i++) sb.append(n);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        nums = new String[K];
        for (int i = 0; i < K; i++) {
            nums[i] = br.readLine();
            if(maxN < Integer.parseInt(nums[i])) maxN = Integer.parseInt(nums[i]);
        }
        System.out.println(solve());
    }
}
