package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10026_적록색약 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static char[][] map;
    private static int notWeakness; // 적록색약이 아닌 사람
    private static int weakness;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};
    private static boolean[][] visited;

    public static boolean isRange(int y, int x) {
        return  0 <= y && y < N && 0 <= x && x < N;
    }

    public static void dfs(int y, int x, char color) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (!isRange(ny, nx) || visited[ny][nx]) continue;
            if (color == map[ny][nx]) dfs(ny, nx, color);
        }
    }

    public static void solve() {
        notWeakness = 0;
        weakness = 0;

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    dfs(i, j, map[i][j]);
                    notWeakness += 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 'G') map[i][j] = 'R';
            }
        }

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    dfs(i, j, map[i][j]);
                    weakness += 1;
                }
            }
        }

        System.out.println(notWeakness + " " + weakness);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        solve();
    }
}
