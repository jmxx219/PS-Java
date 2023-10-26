package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 오목_2615 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static final int N = 19;
    private static int[][] map;
    private static int[] win;
    private static int [][] dir = {{0, 1}, {1, 0}, {1, 1}, {-1, 1}};

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static boolean isWin(int y, int x, int color) {
        for (int i = 0; i < 4; i++) {
            int ny = y;
            int nx = x;
            int cnt = 1;

            while (true) {
                ny += dir[i][0];
                nx += dir[i][1];
                if(!isRange(ny, nx)) break;
                if(color == map[ny][nx]) cnt += 1;
                else break;
            }

            if(cnt == 5) {
                ny = y - dir[i][0];
                nx = x - dir[i][1];
                if(isRange(ny, nx) && color == map[ny][nx]) return false;
                return true;
            }
        }

        return false;
    }

    private static int solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1 || map[i][j] == 2) {
                    if(isWin(i, j, map[i][j])) {
                        win[0] = i + 1;
                        win[1] = j + 1;
                        return map[i][j];
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        win = new int[2];
        int res = solve();
        System.out.println(res);
        if (res != 0) System.out.println(win[0] + " " + win[1]);
    }
}
