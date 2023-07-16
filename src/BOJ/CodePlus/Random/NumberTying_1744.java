package BOJ.CodePlus.Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberTying_1744 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] nums;

    private static int solve() {
        Arrays.sort(nums);

        int index = N - 1;
        while (index >= 0 && nums[index] > 0) index -= 1;
        if(index == -1) index = 0;

        int left = (nums[index] <= 0) ? index : index - 1;
        int right = (nums[index] > 0) ? index : index + 1;

        int res = 0;
        for (int i = 0; i <= left; i++) {
            if(i + 1 <= left) {
                res += (nums[i] * nums[i + 1]);
                i += 1;
            }
            else res += nums[i];
        }

        for (int i = N - 1; i >= right; i--) {
            if(i - 1 >= right) {
                if(nums[i] == 1 || nums[i - 1] == 1) {
                    res +=  (nums[i] + nums[i - 1]);
                }
                else res +=  (nums[i] * nums[i - 1]);
                i -= 1;
            }
            else res += nums[i];
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        nums = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve());
    }
}
