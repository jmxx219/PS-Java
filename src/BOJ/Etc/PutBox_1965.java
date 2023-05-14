package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class PutBox_1965 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] size;

    public static int solve() {
        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        int res = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if(size[i] > size[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        size = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            size[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }
}
