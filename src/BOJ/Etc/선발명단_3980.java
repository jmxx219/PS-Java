package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Pass(10m)
public class 선발명단_3980 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int C;
    private static final int N = 11;
    private static int[][] S;
    private static int res;

    private static void solve(int index, int currSum, boolean[] check) {
        if(index == N) {
            res = Math.max(res, currSum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!check[i] && S[i][index] != 0) {
                check[i] = true;
                solve(index + 1, currSum + S[i][index], check);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());

        while (C-- > 0) {
            S = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            res = 0;
            solve(0, 0, new boolean[N]);
            System.out.println(res);
        }

    }
}
