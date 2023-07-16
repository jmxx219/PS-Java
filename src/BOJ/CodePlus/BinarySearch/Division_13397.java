package BOJ.CodePlus.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Division_13397 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[] nums;

    public static boolean isOk(int diff) {
        int ans = 1;
        int t1 = nums[0];
        int t2 = nums[0];

        for (int i = 1; i < N; i++) {
            if(t1 > nums[i]) t1 = nums[i];
            if(t2 < nums[i]) t2 = nums[i];
            if(t2 - t1 > diff) {
                ans += 1;
                t1 = t2 = nums[i];
            }
        }

        return ans <= M;
    }

    private static int solve() {
        int left = 0;
        int right = 0;
        for (int i = 0; i < N; i++) {
            if(right < nums[i]) right = nums[i];
        }

        int ans = right;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(isOk(mid)) {
                ans = mid;
                right = mid - 1;
            }
            else left = mid + 1;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }
}
