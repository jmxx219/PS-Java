package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 파리퇴치3_12712 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int N;
    private static int M;
    private static int[][] map;
    private static final int[] dy = {1, -1, 0, 0, -1, -1, 1, 1};
    private static final int[] dx = {0, 0, 1, -1, -1, 1, -1, 1};

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static int countDir(int y, int x, int i, int m) {
        if(m == 0) return 0;
        int ny = y + dy[i];
        int nx = x + dx[i];
        if(!isRange(ny, nx)) return 0;
        return map[ny][nx] + countDir(ny, nx, i, m - 1);
    }

    private static int countMaxFly(int y, int x) {
        int countPlus = map[y][x];
        int countX = map[y][x];
        for (int i = 0; i < 8; i++) {
            if(i < 4) countPlus += countDir(y, x, i, M - 1);
            else countX += countDir(y, x, i, M - 1);
        }
        return Math.max(countPlus, countX);
    }

    private static int solve() {
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res = Math.max(res, countMaxFly(i, j));
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int j = 0; j < N; j++) {
                map[j] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
            }
            System.out.println("#" + (i + 1) + " " + solve());
        }
    }
}
