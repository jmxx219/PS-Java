package BOJ.CodePlus.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GuitarLesson_2343 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[] lesson;

    public static boolean isOk(int size) {
        int cnt = 1;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            if(sum + lesson[i] > size) {
                cnt += 1;
                sum = lesson[i];
            }
            else sum += lesson[i];
        }

        return cnt <= M;
    }

    private static int solve() {
        int left = 0;
        int right = 0;
        for (int i = 0; i < N; i++) {
            if(left < lesson[i]) left = lesson[i];
            right += lesson[i];
        }

        int ans = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(isOk(mid)) {
                ans = mid;
                right = mid - 1;
            }
            else left = mid + 1;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lesson = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lesson[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }
}
