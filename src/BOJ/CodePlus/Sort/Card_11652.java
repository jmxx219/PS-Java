package BOJ.CodePlus.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Card_11652 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static long[] nums;

    public static long solve() {
        Arrays.sort(nums);

        long res = nums[0];
        int resCnt = 1;
        int tmpCnt = 1;

        for (int i = 1; i < N; i++) {
            if(nums[i - 1] == nums[i]) tmpCnt+= 1;
            else tmpCnt = 1;

            if(resCnt < tmpCnt) {
                resCnt = tmpCnt;
                res = nums[i];
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        nums = new long[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Long.parseLong(st.nextToken());
        }
        
        System.out.println(solve());
    }
}
