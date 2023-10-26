package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// F
public class 비숍_1799 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] map;
    private static int res;
    private static final int[] dy = {-1, -1};
    private static final int[] dx = {-1, 1};

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static boolean isOk(int y, int x, boolean[][] bishop) {
        for (int i = 0; i < 2; i++) {
            int ny = y;
            int nx = x;
            while (true) {
                ny += dy[i];
                nx += dx[i];
                if(!isRange(ny, nx)) break;
                if(bishop[ny][nx]) return false;
            }
        }
        return true;
    }

    private static void solve(int y, int x, int cnt, boolean[][] bishop) {
        if(y == N) {
            res = Math.max(res, cnt);
            return;
        }

        if(x == N){
            solve(y + 1, 0, cnt, bishop);
            return;
        }

        if(map[y][x] == 1 && !bishop[y][x] && isOk(y, x, bishop)) {
            bishop[y][x] = true;
            solve(y, x + 1, cnt + 1, bishop);
            bishop[y][x] = false;
        }
        solve(y, x + 1, cnt, bishop);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = 0;
        solve(0, 0, 0, new boolean[N][N]);
        System.out.println(res);
    }
}
