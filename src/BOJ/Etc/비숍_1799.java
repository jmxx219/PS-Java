package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// FFFF -> 시간초과(흑색과 백색 칸 구분)
public class 비숍_1799 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] map;
    private static int black_cnt;
    private static int white_cnt;
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

    private static void solve(int y, int x, int cnt, boolean black, boolean[][] bishop) {
        if(y >= N) {
            if(black) black_cnt = Math.max(black_cnt, cnt);
            else white_cnt = Math.max(white_cnt, cnt);
            return;
        }

        if(x >= N){
            int ny = y + 1;
            int nx = ((black && ny % 2 == 0) || (!black && ny % 2 == 1)) ? 0 : 1;
            solve(ny, nx, cnt, black, bishop);
            return;
        }

        if(map[y][x] == 1 && !bishop[y][x] && isOk(y, x, bishop)) {
            bishop[y][x] = true;
            solve(y, x + 2, cnt + 1, black, bishop);
            bishop[y][x] = false;
        }
        solve(y, x + 2, cnt, black, bishop);
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

        black_cnt = white_cnt = 0;
        solve(0, 0, 0, true, new boolean[N][N]);
        solve(0, 1, 0, false, new boolean[N][N]);
        int res = black_cnt + white_cnt;
        System.out.println(res);
    }
}
