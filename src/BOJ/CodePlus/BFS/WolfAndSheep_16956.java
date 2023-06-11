package BOJ.CodePlus.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class WolfAndSheep_16956 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int R;
    private static int C;
    private static char[][] map; // S: 양, W: 늑대, .:빈 칸
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};

    private static boolean isRange(int y, int x){
        return 0 <= y && y < R && 0 <= x && x < C;
    }

    private static void setFence(int y, int x) {
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(!isRange(ny, nx)) continue;

            if(map[ny][nx] == '.') map[ny][nx] = 'D';
        }
    }

    private static int solve() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'S') setFence(i, j);
                if(map[i][j] == 'W') {
                    queue.add(new int[] {i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] here = queue.poll();

            if(map[here[0]][here[1]] == 'S') return 0;

            for (int i = 0; i < 4; i++) {
                int ny = here[0] + dy[i];
                int nx = here[1] + dx[i];
                if(!isRange(ny, nx)) continue;

                if(!visited[ny][nx] && map[ny][nx] != 'D') {
                    visited[ny][nx] = true;
                    queue.add(new int[] {ny, nx});
                }
            }
        }

        return 1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int res = solve();
        System.out.println(res);
        if(res != 0) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }
}
