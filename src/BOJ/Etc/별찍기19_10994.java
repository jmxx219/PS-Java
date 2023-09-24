package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Pass(25m)
public class 별찍기19_10994 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int H;
    private static boolean[][] A;

    public static void solve(int n, int h) {
        if(n == N) return;

        int start = 2 * n;
        int end = 2 * n + h - 1;
        for (int j = start; j <= end; j++) {
            A[start][j] = true;
            A[end][j] = true;
        }
        for (int i = start; i <= end; i++) {
            A[i][start] = true;
            A[i][end] = true;
        }
        solve(n + 1, h - 4);

    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        H = (N - 1) * 4 + 1;
        A = new boolean[H][H];

        solve(0, H);

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < H; j++) {
                if(A[i][j]) System.out.print('*');
                else System.out.print(' ');
            }
            System.out.println();
        }

    }

}
