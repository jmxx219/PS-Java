package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자_배열_회전_1961 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int N;
    private static int[][] map;
    private static String[][] res;

    private static void solve() {
        int index = 0;
        for(int j = 0; j < N; j++) {
            String tmp = "";
            for(int i = N -1; i >= 0; i--) {
                tmp += String.valueOf(map[i][j]);
            }
            res[index++][0] = tmp;
        }

        index = 0;
        for(int i = N -1; i >= 0; i--) {
            String tmp = "";
            for(int j = N -1; j >= 0; j--) {
                tmp += String.valueOf(map[i][j]);
            }
            res[index++][1] = tmp;
        }

        index = 0;
        for(int j = N - 1; j >= 0; j--) {
            String tmp = "";
            for(int i = 0; i < N; i++) {
                tmp += String.valueOf(map[i][j]);
            }
            res[index++][2] = tmp;
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            res = new String[N][3];
            map = new int[N][N];
            for (int j = 0; j < N; j++) {
                map[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            solve();
            System.out.println("#" + (i + 1));
            for(int k = 0; k < N; k++) {
                for(int j = 0; j < 3; j++) {
                    System.out.print(res[k][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
