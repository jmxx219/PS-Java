package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Downhill_1520 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] map;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};
    private static long[][] dp;

    private static void print(long[][] dp) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isRange(int y, int x){
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static long dfs(int y, int x) {
        if(y == N -1 && x == M - 1) return 1;

        if(dp[y][x] != -1) return dp[y][x];

        dp[y][x] = 0;
        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];
            if (!isRange(ny, nx)) continue;
            if(map[y][x] > map[ny][nx]) {
                dp[y][x] += dfs(ny, nx);
            }
        }

        return dp[y][x];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new long[N][M];
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));
    }
}
