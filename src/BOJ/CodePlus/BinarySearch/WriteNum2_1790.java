package BOJ.CodePlus.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// F
public class  WriteNum2_1790 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static long K;

    public static long calc(int n) {
        long cnt = 0;
        int len = 1;
        for (int start = 1; start <= n; start *= 10) {
            int end = start * 10 - 1;
            if(n < end) {
                end = n;
            }
            cnt += (long) (end - start + 1) * len;
            len += 1;
        }
        return cnt;
    }

    public static int solve() {
        if(calc(N) < K) return -1;

        int start = 1;
        int end = N;
        int ans = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            long len = calc(mid);
            if(len < K) {
                start = mid + 1;
            }
            else {
                ans = mid;
                end = mid - 1;
            }
        }

        String s = Integer.toString(ans);
        long l = calc(ans);
        return s.charAt(s.length() - (int)(l - K) - 1) - '0';
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        System.out.println(solve());
    }
}
