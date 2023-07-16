package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfNumbers2_2003 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[] A;

    private static int solve() {
        int ans = 0;
        int p1 = 0, p2 = 0;
        int pSum = A[0];

        while (p2 < A.length) {
            if(pSum > M) {
                pSum -= A[p1];
                p1 += 1;
            }
            else {
                if(pSum == M) ans += 1;
                p2 += 1;
                if(p2 < A.length) pSum += A[p2];
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }
}
