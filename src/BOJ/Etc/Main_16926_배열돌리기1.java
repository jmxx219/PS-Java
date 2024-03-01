package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926_배열돌리기1 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int R;
    private static int[][] A;
    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {1, 0, -1, 0};

    public static boolean isRange(int y, int x, int i) {
        return i <= y && y < N - i && i <= x && x < M - i;
    }

    private static void rotation() {
        int cnt = Math.min(N, M) / 2;
        for (int i = 0; i < cnt; i++) {
            int y = i, x = i;
            int first = A[y][x];

            int dir = 0;
            while (dir < 4) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if(isRange(ny, nx, i)) {
                    A[y][x] = A[ny][nx];
                    y = ny;
                    x = nx;
                }
                else dir += 1;
            }
            A[i + 1][i] = first;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (R-- > 0) rotation();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }

    }
}
