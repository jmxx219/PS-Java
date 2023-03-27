package BOJ.CodePlus.DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z_1074 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int R;
    private static int C;
    private static int res = 0;

    private static boolean isRange(int r, int c, int size) {
        return r <= R && R < r + size && c <= C && C < c + size;
    }

    private static boolean solve(int r, int c, int size, int cnt) {
        if(size == 1) {
            if(r == R && c == C) {
                res = cnt;
                return true;
            }
            return false;
        }

        int nSize = size / 2;
        int x = 0;
        int nCnt = cnt;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int nr = r + i * nSize;
                int nc = c + j * nSize;
                nCnt = cnt + (nSize * nSize) * x;
                if(isRange(nr, nc, nSize)) {
                    if(solve(nr, nc, nSize, nCnt)) return true;
                }
                x += 1;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        solve(0, 0, (int) Math.pow(2, N), 0);
        System.out.println(res);
    }
}
