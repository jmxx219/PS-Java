package BOJ.CodePlus.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CutTree_2805 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static long M;
    private static long[] height;
    private static long maxHeight = Long.MIN_VALUE;


    public static long solve() {
        long start = 0;
        long end = maxHeight;

        while (start <= end) {
            long mid = (start + end) / 2;
            long sum = 0;
            for (int i = 0; i < N; i++) {
                if (height[i] - mid > 0) {
                    sum += height[i] - mid;
                }
            }
            if (sum >= M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return end;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        height = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            height[i] = Long.parseLong((st.nextToken()));
            if(maxHeight < height[i]) maxHeight = height[i];
        }

        System.out.println(solve());
    }
}
