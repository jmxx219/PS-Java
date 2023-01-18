package BOJ.CodePlus.Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FactorialZero_1676 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int res = 0;
        for (int i = 5; i <= N; i *= 5) {
            res += N / i;
        }
        System.out.println(res);
    }
}
