package BOJ.CodePlus.DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TowerOfHanoi_11729 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;

    public static void solve(int n, int from, int to, StringBuilder sb) {
        if(n == 0) return;
        solve(n - 1, from, 6-from-to, sb);
        sb.append(from + " " + to + "\n");
        solve(n - 1, 6-from-to, to, sb);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        System.out.println((1 << N) - 1);
        StringBuilder sb = new StringBuilder();
        solve(N, 1, 3, sb);
        System.out.println(sb);
    }
}
