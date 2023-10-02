package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 무기공학_18430 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] K;
    private static int res;
    private static final int[][][] dir = {
            {{0, -1}, {1, 0}},
            {{-1, 0}, {0, -1}},
            {{-1, 0}, {0, 1}},
            {{0, 1}, {1, 0}}
    };

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static void solve(int index, int currSum, boolean[][] check) {
        if(index == N * M) {
            res = Math.max(res, currSum);
            return;
        }

        int y = index / M;
        int x = index % M;

        if(check[y][x]) {
            solve(index + 1, currSum, check);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny1 = y + dir[i][0][0];
            int nx1 = x + dir[i][0][1];
            int ny2 = y + dir[i][1][0];
            int nx2 = x + dir[i][1][1];

            if(!isRange(ny1, nx1) || !isRange(ny2, nx2)) continue;

            if(!check[ny1][nx1] && !check[ny2][nx2]) {
                check[y][x] = check[ny1][nx1] = check[ny2][nx2] = true;
                int tempSum = K[ny1][nx1] + K[ny2][nx2] + K[y][x] * 2;
                solve(index + 1, currSum + tempSum, check);
                check[y][x] = check[ny1][nx1] = check[ny2][nx2] = false;
            }
        }
        solve(index + 1, currSum, check);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        K = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                K[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = 0;
        solve(0, 0, new boolean[N][M]);
        System.out.println(res);
    }
}
