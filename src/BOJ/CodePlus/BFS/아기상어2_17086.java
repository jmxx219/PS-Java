package BOJ.CodePlus.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Pass(10m)
public class 아기상어2_17086 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] map;
    private static int[] dy = {0, 0, 1, -1, -1, -1, 1, 1};
    private static int[] dx = {1, -1, 0, 0, -1, 1, -1, 1};


    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <=  x && x < M;
    }


    private static int solve() {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1) queue.add(new int[] {i, j});
            }
        }

        while (!queue.isEmpty()) {
            int[] here = queue.poll();

            for (int i = 0; i < 8; i++) {
                int ny = here[0] + dy[i];
                int nx = here[1] + dx[i];

                if(!isRange(ny, nx)) continue;

                if(map[ny][nx] == 0) {
                    map[ny][nx] = map[here[0]][here[1]] + 1;
                    queue.add(new int[] {ny, nx});
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cnt = Math.max(cnt, map[i][j]);
            }
        }

        return cnt - 1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());

    }
}
