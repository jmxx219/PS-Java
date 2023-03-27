package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    이거는 진짜 모르겠다, , , ! ! ! 시간 초과 왜 ? ! ! !
 */
public class BreakWallAndMove3_16933 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int K;
    private static int[][] map;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};
    private static final int INF = 987654321;

    public static class Point {
        int y, x;
        int k;
        boolean isDayTime;
        int dist;

        public Point(int y, int x, int k, boolean isDayTime, int dist) {
            this.y = y;
            this.x = x;
            this.k = k;
            this.isDayTime = isDayTime;
            this.dist = dist;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static int solve() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][K + 1][2];

        queue.add(new Point(0, 0, 0, true, 1));
        visited[0][0][0][0] = true;

        int res = INF;
        while (!queue.isEmpty()) {
            Point here = queue.poll();
            int y = here.y;
            int x = here.x;
            int k = here.k;

            if(y == N-1 && x == M-1){
                if(res > here.dist) res = here.dist;
            }

            boolean nextDayTime = !here.isDayTime;
            int nextDist = here.dist + 1;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(!isRange(ny, nx)) continue;

                // 벽이 아닐 경우
                if(map[ny][nx] == 0 && !visited[ny][nx][k][nextDayTime ? 0 : 1]) {
                    visited[ny][nx][k][nextDayTime ? 0 : 1] = true;
                    queue.add(new Point(ny, nx, k, nextDayTime, nextDist));
                }

                // 벽이면서 낮일 경우
                if(here.isDayTime && map[ny][nx] == 1 && k < K && !visited[ny][nx][k + 1][1]) {
                    visited[ny][nx][k + 1][1] = true;
                    queue.add(new Point(ny, nx, k + 1, false, nextDist));
                }
            }

            if(!visited[y][x][k][nextDayTime ? 0 : 1]) {
                visited[y][x][k][nextDayTime ? 0 : 1] = true;
                queue.add(new Point(y, x, k, nextDayTime, nextDist));
            }
        }

        return res == INF ? -1 : res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        System.out.println(solve());
    }
}