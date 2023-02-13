package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AllPermutation_10974 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] picked;

    public static void solve(int index, boolean[] visited) {
        if(index == N) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < N; i++) {
                sb.append(picked[i]);
                if(i != N - 1) sb.append(" ");
            }
            System.out.println(sb);
        }

        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                picked[index] = i;
                solve(index + 1, visited);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        picked = new int[N];
        solve(0, new boolean[N + 1]);
    }
}
