package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class NM9_15663 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[] nums;
    private static int[] picked;
    private static Set<String> result;

    public static StringBuilder solve(int index, boolean[] visited) {
        if(index == M) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < M; i++) {
                sb.append(nums[picked[i]]);
                if(i != M - 1) sb.append(" ");
            }
            if(!result.contains(String.valueOf(sb))) {
                result.add(String.valueOf(sb));
                sb.append("\n");
                return sb;
            }
            return new StringBuilder();
        }

        StringBuilder res = new StringBuilder();
        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                picked[index] = i;
                res.append(solve(index + 1, visited));
                visited[i] = false;
            }
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

        result = new HashSet<>();
        System.out.println(solve(0, new boolean[N]));
    }
}
