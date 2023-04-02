package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NQueen_9663 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] cols;

    public static boolean isOk(int row) { // 행은 항상 다름, 열이랑 대각선만 체크
        for (int r = 0; r < row; r++) {
            if(cols[r] == cols[row] || Math.abs(cols[row] - cols[r]) == (row - r)) return false;
        }
        return true;
    }

    public static int solve(int row) {
        int cnt = 0;

        for (int c = 0; c < N; c++) {
            cols[row] = c;
            if(isOk(row)) {
                if(row + 1 == N) return 1;
                else cnt += solve(row + 1);
            }

        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        cols = new int[N];

        System.out.println(solve(0));
    }
}
