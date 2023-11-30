package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.LinkOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 두_배열의_합_2143 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int N;
    private static int M;
    private static int[] A;
    private static int[] B;

    private static long solve() {
        long res = 0L;
        Map<Long, Long> aSums = new HashMap<>();

        for (int i = 0; i < N; i++) {
            long sum = 0;
            for (int j = i; j < N; j++) {
                sum += A[j];
                aSums.put(sum, aSums.getOrDefault(sum, 0L) + 1);
            }
        }

        for (int i = 0; i < M; i++) {
            long sum = 0;
            for (int j = i; j < M; j++) {
                sum += B[j];
                res += aSums.getOrDefault(T - sum, 0L);
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solve());
    }
}
