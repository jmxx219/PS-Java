package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DivideThreeMultiTwo_16936 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static long[] B;
    private static long[] picked;
    private static boolean[] check;

    private static boolean isOk(int index, long curr) {
        long prev = picked[index - 1];
        boolean ok = false;
        if(prev % 3 == 0 && (prev / 3) == curr) ok = true;
        if(prev * 2 == curr) ok = true;
        return ok;
    }

    private static boolean solve(int index) {
        if(index == N) {
            for (int i = 0; i < N; i++) {
                System.out.print(picked[i] + " ");
            }
            System.out.println();
            return true;
        }

        for (int i = 0; i < N; i++) {
            if(check[i]) continue;
            if(index == 0 || (index > 0 && isOk(index, B[i]))) {
                check[i] = true;
                picked[index] = B[i];
                if(solve(index + 1)) return true;
                check[i] = false;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        picked = new long[N];
        check = new boolean[N];
        solve(0);
    }
}
