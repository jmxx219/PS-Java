package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 스도쿠_검증_1974 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static final int N = 9;
    private static int[][] map;

    private static int solve() {
        for (int i = 0; i < N; i++) {
            boolean[] row = new boolean[N + 1];
            boolean[] col = new boolean[N + 1];
            for (int j = 0; j < N; j++) {
                if(row[map[i][j]]) return 0;
                if(col[map[j][i]]) return 0;

                row[map[i][j]] = true;
                col[map[j][i]] = true;
            }
        }

        for (int k = 0; k < N; k++) {
            int y = (k / 3) * 3;
            int x = (k % 3) * 3;
            boolean[] check = new boolean[N + 1];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int ny = y + i;
                    int nx = x + j;
                    if(check[map[ny][nx]]) return 0;
                    check[map[ny][nx]] = true;
                }
            }
        }

        return 1;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            map = new int[N][N];
            for (int j = 0; j < N; j++) {
                map[j] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
            }
            System.out.println("#" + (i + 1) + " " + solve());
        }
    }
}
