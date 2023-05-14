package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ramp_14890 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int L;
    private static int[][] map;

    public static boolean isOk(int k, int flag) {
        int[] height = new int[N];
        boolean[] visited = new boolean[N];// 경사로 체크

        for (int i = 0; i < N; i++) {
            if (flag == 0) height[i] = map[k][i]; // row
            else height[i] = map[i][k]; // col
        }

        for (int i = 0; i < N - 1; i++) {
            if(height[i] == height[i + 1]) continue;
            else if(Math.abs(height[i] - height[i + 1]) > 1) return false;
            else if(height[i] + 1 == height[i + 1]) { // 오르막
                for (int j = i; j > i - L; j--) {
                    if(j < 0 || height[i] != height[j] || visited[j]) return false;
                    visited[j] = true;
                }
            }
            else if(height[i] - 1 == height[i + 1]) { // 내리막
                for (int j = i + 1; j <= i + L; j++) {
                    if(j >= N || height[i + 1] != height[j] || visited[j]) return false;
                    visited[j] = true;
                }
            }
        }

        return true;
    }

    public static int solve() {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            if(isOk(i, 0)) cnt += 1;
            if(isOk(i, 1)) cnt += 1;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }
}
