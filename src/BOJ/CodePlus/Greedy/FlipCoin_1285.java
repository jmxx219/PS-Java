package BOJ.CodePlus.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FlipCoin_1285 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static char[][] coin;
    private static int res = Integer.MAX_VALUE;

    private static char flip(char x) {
        if (x == 'H') return 'T';
        return 'H';
    }

    private static int solve() {
        int ans = N * N;

        for (int row = 0; row < (1 << N); row++) { // 행 뒤집기 부분집합(바꿀지, 말지)
            int sum = 0;
            for (int j = 0; j < N; j++) {
                int backCnt = 0;
                for (int i = 0; i < N; i++) {
                    char curr = coin[i][j];

                    if((row & (1 << i)) != 0) {
                        curr = flip(curr);
                    }

                    if(curr == 'T') backCnt += 1;
                }
                sum += Math.min(backCnt, N - backCnt);
            }
            if(sum < ans) ans = sum;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        coin = new char[N][N];
        for (int i = 0; i < N; i++) {
            coin[i] = br.readLine().toCharArray();
        }


        System.out.println(solve());
    }
}
