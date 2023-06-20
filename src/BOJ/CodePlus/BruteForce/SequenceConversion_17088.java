package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SequenceConversion_17088 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] B;
    private static final int[] op = {0, -1, 1};

    private static int conversion(int diff, int[] A) {

        int cnt = 0;
        for (int i = 2; i < N; i++) {
            boolean isOk = false;
            for (int j = 0; j < 3; j++) {
                if((A[i] + op[j]) - A[i - 1] == diff) {
                    cnt += (j == 0) ? 0 : 1;
                    A[i] += op[j];
                    isOk = true;
                }
            }
            if(!isOk) return -1;
        }

        return cnt;
    }

    private static int count(int i, int j) {
        if(i == 0 && j == 0) return 0;
        else if(i != 0 && j != 0) return 2;
        return 1;
    }

    private static int solve() {
        if(N <= 2) return 0;

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] A = B.clone();
                A[0] = B[0] + op[i];
                A[1] = B[1] + op[j];
                int cnt = conversion(A[1] - A[0], A);
                if(cnt != -1) {
                    if(res > cnt + count(i, j)) res = cnt + count(i, j);
                }
            }
        }

        return (res >= Integer.MAX_VALUE) ? -1 : res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        B = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }
}
