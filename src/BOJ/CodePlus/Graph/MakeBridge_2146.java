package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MakeBridge_2146 {

    public static class Point {
        public int y;
        public int x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] map;
    private static int[][] dist;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};
    private static Queue<Point> queue;
    private static boolean[][] visited;


    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static int bfs() {
        while (!queue.isEmpty()) {
            Point here = queue.poll();

            for(int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if(!isRange(ny, nx)) continue;

                if(dist[ny][nx] == -1) {
                    map[ny][nx] = map[here.y][here.x];
                    queue.add(new Point(ny, nx));
                    dist[ny][nx] = dist[here.y][here.x] + 1;
                }
            }
        }


        return 0;
    }

    private static void dfs(int y, int x, int color) {
        visited[y][x] = true;
        dist[y][x] = 0;
        map[y][x] = color;

        boolean check = false;
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(isRange(ny, nx) && !visited[ny][nx]) {
                if(map[ny][nx] == 1) dfs(ny, nx, color);
                else if(!check) {
                    check = true;
                    queue.add(new Point(y, x));
                }
            }
        }
    }

    private static int solve() {
        queue = new LinkedList<>();
        dist = new int[N][N];

        for(int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        visited = new boolean[N][N];
        int color = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j, color);
                    color += 1;
                }
            }
        }

        bfs();

        int res = Integer.MAX_VALUE;
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if(isRange(ny, nx) && map[y][x] != map[ny][nx]) {
                        res = Math.min(res, dist[y][x] + dist[ny][nx]);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solve());
    }
}
