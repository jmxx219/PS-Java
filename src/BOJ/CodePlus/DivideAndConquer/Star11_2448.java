package BOJ.CodePlus.DivideAndConquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Star11_2448 {
    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static char[][] stars;
    private final static char STAR = '*';
    private final static char BLANK = ' ';

    public static void solve(int y, int x, int size) {
        if(size == 3) {
            stars[y][x] = STAR;
            stars[y + 1][x - 1] = stars[y + 1][x + 1] = STAR;
            for (int i = -2; i <= 2; i++) {
                stars[y + 2][x + i] = STAR;
            }
            return;
        }

        int nSize = size / 2;
        solve(y, x, nSize);
        solve(y + nSize, x - nSize, nSize);
        solve(y + nSize, x + nSize, nSize);
    }

    public static void main(String[] args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        stars = new char[N][N * 2 - 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(stars[i], BLANK);
        }

        solve(0, N - 1, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N -1; j++) {
                bw.write(stars[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
