package BOJ.CodePlus.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Painting_1926 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] paint;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static int bfs(int y, int x, int color) {
        int cnt = 1;
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {y, x});
        paint[y][x] = color;

        while (!queue.isEmpty()) {
            int[] here = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = here[0] + dy[i];
                int nx = here[1] + dx[i];

                if(!isRange(ny, nx)) continue;

                if(paint[ny][nx] == 1) {
                    paint[ny][nx] = color;
                    queue.add(new int[] {ny, nx});
                    cnt += 1;
                }

            }
        }

        return cnt;
    }

    private static void solve() {
        int res = 0;
        int color = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(paint[i][j] == 1) {
                    res = Math.max(res, bfs(i, j, color));
                    color += 1;
                }
            }
        }

        System.out.println(color - 2);
        System.out.println(res);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paint = new int[N][M];
        for (int i = 0; i < N; i++) {
            paint[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        solve();
    }
}
