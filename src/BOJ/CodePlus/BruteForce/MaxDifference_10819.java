package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaxDifference_10819 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] nums;
    private static int[] picked;
    private static int maxDiff = 0;

    public static void solve(int index, boolean[] visited) {
        if(index == N) {
            int diff = 0;
            for(int i = 1; i < N; i++) {
                diff += Math.abs(picked[i] - picked[i - 1]);
            }
            maxDiff = Math.max(maxDiff, diff);
        }

        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                picked[index] = nums[i];
                solve(index + 1, visited);
                visited[i] = false;
            }
        }
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

        picked = new int[N];
        solve(0, new boolean[N + 1]);
        System.out.println(maxDiff);
    }
}
