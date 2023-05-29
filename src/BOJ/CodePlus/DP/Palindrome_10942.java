package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Palindrome_10942 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] A;
    private static int M;
    private static int[][] D;

    private static boolean[][] bottom_up() {
        boolean[][] dp = new boolean[N][N];

        for (int i = 0; i < N; i++) dp[i][i] = true; // 글자 수 1개
        for (int i = 0; i < N - 1; i++) { // 글자 수 2개
            if(A[i] == A[i + 1]) dp[i][i + 1] = true;
        }

        // 글자 수 3개 이상
        for (int k = 2; k < N; k++) {
            for (int i = 0; i < N - k; i++) {
                int j = i + k;
                if(A[i] == A[j] && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }

        return dp;
    }
    private static int top_down(int i, int j) {
        if(i == j) return 1;
        else if(i + 1 == j) return (A[i] == A[j]) ? 1 : 0;

        if(D[i][j] != -1) return D[i][j];

        if(A[i] != A[j]) D[i][j] = 0;
        else D[i][j] = top_down(i + 1, j - 1);

        return D[i][j];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        A = new int[N];
        D = new int[N][N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(D[i], -1);
        }

        boolean[][] dp = bottom_up();

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()) - 1;
            int E = Integer.parseInt(st.nextToken()) - 1;
            sb.append(dp[S][E] ? 1 : 0);
//            sb.append(top_down(S, E));
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
