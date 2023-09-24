package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Pass(30m)
public class 달팽이_1913 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] res;
    private static int[] point;
    private static int[] dy = {1, 0, -1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static boolean isOk(int ny, int nx) {
        if(ny < 0 || ny >= N || nx < 0 || nx >= N) return false;
        if(res[ny][nx] != 0) return false;
        return true;
    }

    private static void solve() {
        int y = 0, x = 0;
        int d = 0;
        for (int i = N * N; i >= 1; i--) {
            if(i == M) point = new int[] {y + 1, x + 1};
            res[y][x] = i;

            int ny = y + dy[d];
            int nx = x + dx[d];

            if(!isOk(ny, nx)){
                d = (d + 1) % 4;
                ny = y + dy[d];
                nx = x + dx[d];
            }

            y = ny;
            x = nx;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        res = new int[N][N];
        solve();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(res[i][j] + " ");
            }
            sb.append("\n");
        }
        sb.append(point[0] + " " + point[1]);

        System.out.println(sb);
    }
}
