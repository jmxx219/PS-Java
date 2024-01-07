package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파핑파핑_지뢰찾기_1868 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int N;
    private static char[][] map;
    private static int[][] bomb;
    private static final int[] dy = {1, -1, 0, 0, -1, -1, 1, 1};
    private static final int[] dx = {0, 0, 1, -1, -1, 1, -1, 1};

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static void initBombCnt() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == '*') {
                    bomb[i][j] = -1;
                    continue;
                }
                int cnt = 0;
                for (int k = 0; k < 8; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if(!isRange(ny, nx)) continue;
                    if(map[ny][nx] == '*') cnt += 1;
                }
                bomb[i][j] = cnt;
            }
        }
    }

    private static void zeroSearch(int y, int x) {
        for (int k = 0; k < 8; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];
            if(!isRange(ny, nx)) continue;

            if(map[ny][nx] == '.') {
                map[ny][nx] = 'x';
                if(bomb[ny][nx] == 0) zeroSearch(ny, nx);
            }
        }
    }

    private static int solve() {
        initBombCnt();

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(bomb[i][j] == 0 && map[i][j] == '.') {
                    map[i][j] = 'x';
                    zeroSearch(i, j);
                    cnt += 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == '.') {
                    cnt += 1;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            map = new char[N][N];
            bomb = new int[N][N];
            for (int j = 0; j < N; j++) {
                map[j] = br.readLine().toCharArray();
            }
            System.out.println("#" + (i + 1) + " " + solve());
        }
    }
}
