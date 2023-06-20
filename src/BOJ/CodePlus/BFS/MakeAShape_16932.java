package BOJ.CodePlus.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class MakeAShape_16932 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] map;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};
    private static Map<Integer, Integer> group;

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static int bfs(int y, int x, int color) {
        int cnt = 1;
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {y, x});
        map[y][x] = color;

        while (!queue.isEmpty()) {
            int[] here = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = here[0] + dy[i];
                int nx = here[1] + dx[i];

                if(!isRange(ny, nx)) continue;

                if(map[ny][nx] == -1) {
                    map[ny][nx] = color;
                    queue.add(new int[] {ny, nx});
                    cnt += 1;
                }

            }
        }

        return cnt;
    }

    private static void makeGroup() {
        int color = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == -1) {
                    group.put(color, bfs(i, j, color));
                    color += 1;
                }
            }
        }
    }

    private static int aroundCount(int y, int x){
        Set<Integer> aroundGroup = new HashSet<>();
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(!isRange(ny, nx)) continue;

            int color = map[ny][nx];
            if(color > 0 && !aroundGroup.contains(color)) {
                aroundGroup.add(color);
                cnt += group.get(color);
            }
        }

        return cnt + 1;
    }

    private static int solve() {
        makeGroup();

        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    res = Math.max(res, aroundCount(i, j));
                }
            }
        }

        return res;
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
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = (x == 1) ? -1 : x;
            }
        }

        group = new HashMap<>();
        System.out.println(solve());
    }
}
