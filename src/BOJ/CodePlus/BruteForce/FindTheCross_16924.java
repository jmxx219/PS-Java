package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FindTheCross_16924 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static char[][] map;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};

    private static boolean isRange(int y, int x){
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static void setTheCross(int y, int x, int size, boolean[][] visited) {
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y;
            int nx = x;
            for (int j = 0; j < size; j++) {
                ny += dy[i];
                nx += dx[i];
                visited[ny][nx] = true;
            }
        }
    }

    private static int countTheCross(int y, int x) {
        int res = -1;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            int cnt = 0;
            while (isRange(ny, nx) && map[ny][nx] == '*') {
                cnt += 1;
                ny += dy[i];
                nx += dx[i];
            }
            if(res == -1 || res > cnt) res = cnt;
        }

        return res;
    }

    private static List<String> solve() {
        boolean[][] visited = new boolean[N][M];
        List<String> res = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == '*') {
                    int cnt = countTheCross(i, j);
                    for (int k = 1; k <= cnt; k++) {
                        res.add((i + 1) + " " + (j + 1) + " " + k);
                    }
                    if(cnt > 0) setTheCross(i, j, cnt, visited);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == '*' && !visited[i][j]) return new ArrayList<>();
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        List<String> res = solve();
        if (res.size() == 0) System.out.println(-1);
        else {
            System.out.println(res.size());
            res.stream().forEach(x -> System.out.println(x));
        }
    }
}
