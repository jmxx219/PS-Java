package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class RoboticVacuum_14503 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] map; // 0: 청소 x, 1: 벽
    private static Point start;
    private static int[] dy = {-1, 0, 1, 0}; // U, R, D, L
    private static int[] dx = {0, 1, 0, -1};
    private static int cleanCnt = 0;

    private static class Point {
        int y, x;
        int direction;

        public Point(int y, int x, int direction) {
            this.y = y;
            this.x = x;
            this.direction = direction;
        }
    }

    public static int reverse(int currD) {
        if(currD == 0) return 2;
        else if(currD == 1) return 3;
        else if(currD == 2) return 0;
        return 1;
    }

    public static int rotation(int currD) {
        if(currD == 0) return 3;
        else if(currD == 1) return 0;
        else if(currD == 2) return 1;
        return 2;
    }

    public static void dfs(int y, int x, int direction) {
        if(map[y][x] == 0) {
            map[y][x] = 2; // 청소 완료
            cleanCnt += 1;
        }

        for (int i = 0; i < 4; i++) {
            direction = rotation(direction);
            int ny = y + dy[direction];
            int nx = x + dx[direction];
            if(map[ny][nx] == 0) {
                dfs(ny, nx, direction);
                return;
            }
        }
        int next = reverse(direction);
        int ny = y + dy[next];
        int nx = x + dx[next];
        if(map[ny][nx] != 1) dfs(ny, nx, direction);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cleanCnt = 0;
        dfs(y, x, d);
        System.out.println(cleanCnt);
    }
}
