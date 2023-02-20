package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoDots_16929 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static char[][] board;
    private static int[][] dist;
    private static boolean[][] visited;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static boolean dfs(int y, int x, char color, int depth) {
        if(visited[y][x]) {
            if(depth - dist[y][x] >= 4) return true;
            return false;
        }

        visited[y][x] = true;
        dist[y][x] = depth;

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(isRange(ny, nx) && color == board[ny][nx]) {
                if(dfs(ny, nx, color, depth + 1)) return true;
            }
        }
        return false;
    }

    private static boolean solve() {
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(visited[i][j]) continue;
                dist = new int[N][M];
                if(dfs(i, j, board[i][j], 1)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for(int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        if (solve()) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
