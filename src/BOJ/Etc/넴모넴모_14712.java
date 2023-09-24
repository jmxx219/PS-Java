package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Pass(30m)
public class 넴모넴모_14712 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;

    public static boolean isOk(boolean[][] check) {
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                if(check[i][j] && check[i][j + 1]
                        && check[i + 1][j] && check[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int solve(int y, int x, boolean[][] check) {
        if(!isOk(check)) return 0;

        if(y == N && x == 0) {
            return 1;
        }

        int ny = (x == M - 1) ? y + 1 : y;
        int nx = (x == M - 1) ? 0 : x + 1;

        check[y][x] = true;
        int a = solve(ny, nx, check);
        check[y][x] = false;
        int b = solve(ny, nx, check);

        return a + b;
    }


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        System.out.println(solve(0, 0, new boolean[N][M]));
    }

}
