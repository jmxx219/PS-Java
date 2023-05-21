package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PlumTree_2240 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T; // 초
    private static int W; // 최대 움직이는 횟수
    private static int[] tree;

    private static int solve() {
        int[][] dp = new int[T + 1][W + 1];

        for (int i = 1; i < T + 1; i++) {
            dp[i][0] = dp[i - 1][0] + (tree[i] == 1 ? 1 : 0);
            for (int j = 1; j <= W && j <= i; j++) {
                int curr = (j % 2 == 0) ? 1 : 2; // 자두 위치
                int fall = (curr == tree[i]) ? 1 : 0; // 자두 위치랑 나무 위치랑 같을 때
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + fall;
            }
        }

        int answer = dp[T][0];
        for (int i = 1; i <= W; i++) {
            answer = Math.max(dp[T][i], answer);
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        tree = new int[T + 1];
        for (int i = 1; i < T + 1; i++) {
            st = new StringTokenizer(br.readLine());
            tree[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve());
    }
}
