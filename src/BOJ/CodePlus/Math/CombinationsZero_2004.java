package BOJ.CodePlus.Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CombinationsZero_2004 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static long N;
    private static long M;

    public static long factorialZeroCnt(long n, int mod) {
        long cnt = 0;
        for (long i = mod; i <= n; i *= mod) {
            cnt += n / i;
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());

        long two = 0, five = 0;
        two = factorialZeroCnt(N, 2) - factorialZeroCnt(N - M, 2) - factorialZeroCnt(M, 2);
        five = factorialZeroCnt(N, 5) - factorialZeroCnt(N - M, 5) - factorialZeroCnt(M, 5);
        System.out.println(Math.min(two, five));
    }
}
