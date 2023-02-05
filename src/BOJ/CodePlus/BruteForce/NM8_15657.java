package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NM8_15657 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[] nums;
    private static int[] picked;

    public static StringBuilder solve(int next, int index) {
        if(index == M) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < M; i++) {
                sb.append(nums[picked[i]]);
                if(i != M - 1) sb.append(" ");
            }
            sb.append("\n");
            return sb;
        }

        StringBuilder res = new StringBuilder();
        for(int i = next; i < N; i++) {
            picked[index] = i;
            res.append(solve(i,index + 1));
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        picked = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        System.out.println(solve(0, 0));
    }
}
