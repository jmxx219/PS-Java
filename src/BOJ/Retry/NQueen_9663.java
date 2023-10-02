package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Pass(20m)
public class NQueen_9663 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;

    private static boolean isOk(int row, int[] cols) {
        int r = 0;
        while (r < row) {
            // 같은 열, 대각선 비교
            if(cols[r] == cols[row] || Math.abs(cols[r] - cols[row]) == (row - r)) {
                return false;
            }
            r += 1;
        }
        return true;
    }

    private static int solve(int row, int[] cols) {
        if(row == N) return 1;

        int cnt = 0;
        for (int c = 0; c < N; c++) {
            cols[row] = c;
            if(isOk(row, cols)) {
                cnt += solve(row + 1, cols);
            }
        }

        return cnt;
    }

    private static int solve2(int row, int[] cols) {
        if(!isOk(row - 1, cols)) return 0;
        if(row == N) return 1;

        int cnt = 0;
        for (int c = 0; c < N; c++) {
            cols[row] = c;
            cnt += solve2(row + 1, cols);
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        System.out.println(solve(0, new int[N]));
        System.out.println(solve2(0, new int[N]));
    }
}
