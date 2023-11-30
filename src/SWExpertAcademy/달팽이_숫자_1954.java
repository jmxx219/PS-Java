package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 달팽이_숫자_1954 {

    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] map;
    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {1, 0, -1, 0};

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static void solve() {
        int index = 1;
        int y = 0;
        int x = 0;
        int dir = 0;
        while (index <= N * N) {
            map[y][x] = index++;

            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if(!isRange(ny, nx) || (isRange(ny, nx) && map[ny][nx] != 0)) {
                dir = (dir + 1) % 4;
            }

            y += dy[dir];
            x += dx[dir];
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            System.out.println("#" + i);
            solve();
            print();
        }
    }
}
