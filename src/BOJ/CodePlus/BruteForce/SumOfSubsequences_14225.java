package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SumOfSubsequences_14225 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] nums;

    private static int solve_bitmask() {
        Arrays.sort(nums);

        Set<Integer> sub = new HashSet<>();
        for (int i = 1; i < (1 << N); i++) {
            int num = 0;
            for (int k = 0; k < N; k++) {
                if((i & (1 << k)) != 0) {
                    num += nums[k];
                }
            }
            sub.add(num);
        }

        for(int n = 1; n < sub.size() + 1; n++) {
            if(!sub.contains(n)) return n;
        }

        return sub.stream().mapToInt(x -> x).max().getAsInt() + 1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve_bitmask());
    }
}
