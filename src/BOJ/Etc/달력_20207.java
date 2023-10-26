package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 달력_20207 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static final int DATE = 365;
    private static int[] calendar;

    private static int solve() {
        int res = 0;

        int width = 0;
        int height = 0;
        for (int i = 1; i <= DATE; i++) {
            if(calendar[i] == 0) {
                res += height * width;
                width = 0;
                height = 0;
            }
            else {
                width += 1;
                height = Math.max(height, calendar[i]);
            }
        }
        res += height * width;

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        calendar = new int[DATE + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            for (int j = start; j <= end; j++) {
                calendar[j] += 1;
            }
        }

        System.out.println(solve());
    }
}
