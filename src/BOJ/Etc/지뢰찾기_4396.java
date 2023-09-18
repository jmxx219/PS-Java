package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Pass(25m)
public class 지뢰찾기_4396 {
    // m개의 지뢰
    // 지뢰가 없는 지점을 건드리면, 상하좌우 혹은 대각선으로 인접한 8개의 칸에 지뢰가 몇 개 있는지 알려줌
    // .: 지뢰 X, *: 지뢰 O
    // x: 이미 열린 칸, .:열리지 않은 칸
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static char[][] map;
    private static char[][] res;
    private static int[] dy = {1, -1, 0, 0, -1, -1, 1, 1};
    private static int[] dx = {0, 0, 1, -1, -1, 1, -1, 1};

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static char count(int y, int x) {
        int cnt = 0;

        for (int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(!isRange(ny, nx)) continue;
            if(map[ny][nx] == '*') cnt += 1;
        }

        return (char) (cnt + '0');
    }

    private static void solve() {
        StringBuilder sb = new StringBuilder();
        boolean isOpenBomb = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(res[i][j] == 'x' && map[i][j] == '*') isOpenBomb = true;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(isOpenBomb && map[i][j] == '*') res[i][j] = '*';
                else if(res[i][j] == 'x' && map[i][j] == '.') {
                    res[i][j] = count(i, j);
                }
                sb.append(res[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        res = new char[N][N];
        for (int i = 0; i < N; i++) {
            res[i] = br.readLine().toCharArray();
        }

        solve();
    }
}