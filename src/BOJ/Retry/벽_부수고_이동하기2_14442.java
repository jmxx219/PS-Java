package BOJ.Retry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽_부수고_이동하기2_14442 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int K;
    private static int[][] map;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};

    static class Point {
        int y, x;
        int k;
        public Point(int y, int x,  int k) {
            this.y = y;
            this.x = x;
            this.k = k;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static int solve() {
        Queue<Point> queue = new LinkedList<>();
        int[][][] dist = new int[N][M][K + 1];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }

        queue.add(new Point(0, 0, 0));
        dist[0][0][0] = 1;

        while(!queue.isEmpty()) {
            Point here = queue.poll();

            for(int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if(!isRange(ny, nx)) continue;

                if(map[ny][nx] == 1 && here.k < K && dist[ny][nx][here.k + 1] == -1) {
                    dist[ny][nx][here.k + 1] = dist[here.y][here.x][here.k] + 1;
                    queue.add(new Point(ny, nx, here.k + 1));
                }

                if(map[ny][nx] == 0 && dist[ny][nx][here.k] == -1) {
                    dist[ny][nx][here.k] = dist[here.y][here.x][here.k] + 1;
                    queue.add(new Point(ny, nx, here.k));
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for(int k = 0; k <= K; k++) {
            if(dist[N - 1][M - 1][k] == -1) continue;
            if(res == -1 || res > dist[N - 1][M - 1][k]) {
                res = dist[N - 1][M - 1][k];
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solve());
    }


}