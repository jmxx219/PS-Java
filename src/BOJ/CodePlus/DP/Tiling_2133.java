package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 타일 채우기
public class Tiling_2133 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;

    public static int tailing() {
        int[] dp = new int[N + 1];
        dp[0] = 1;

        for (int i = 2; i <= N; i += 2) {
            dp[i] = dp[i - 2] * 3;
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2;
            }
        }

        return dp[N];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        System.out.println(tailing());
    }
}
