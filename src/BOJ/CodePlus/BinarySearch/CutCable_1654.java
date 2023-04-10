package BOJ.CodePlus.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CutCable_1654 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static long[] cable;
    private static long maxLen = Integer.MIN_VALUE;

    public static int calcCableCount(long cLen) {
        int res = 0;
        for (int i = 0; i < K; i++) {
            res += cable[i] / cLen;
        }
        return res;
    }

    public static long solve() {
        long start = 1;
        long end = maxLen;

        while (start <= end) {
            long mid = start + (end - start) / 2;

            if(calcCableCount(mid) < N) end = mid - 1;
            else start = mid + 1;
        }

        return end;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        cable = new long[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            cable[i] = Long.parseLong(st.nextToken());
            if(maxLen < cable[i]) maxLen = cable[i];
        }

        System.out.println(solve());
    }
}
