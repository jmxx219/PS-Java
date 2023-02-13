package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Resign_14501 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] consulting;

    private static int solve(int day, int amount) {
        if(day > N) {
            return 0;
        }

        int res = 0;
        for(int i = day; i < N; i++) {
            res = Math.max(res, solve(i + consulting[i][0], consulting[i][1]));
        }

        return res + amount;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        consulting = new int[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            consulting[i][0] = Integer.parseInt(st.nextToken()); // 기간
            consulting[i][1] = Integer.parseInt(st.nextToken()); // 금액
        }

        System.out.println(solve(0, 0));
    }
}
