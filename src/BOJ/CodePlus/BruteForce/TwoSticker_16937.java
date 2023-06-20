package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoSticker_16937 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int H;
    private static int W;
    private static int N;
    private static int[][] sticker;
    private static int[] picked;

    private static boolean isRange(int y, int x) {
        return 0 <= y && y <= H && 0 <= x && x <= W;
    }

    private static boolean isOk(int y1, int x1, int y2, int x2) {
        return isRange(Math.max(y1, y2), (x1 + x2)) || isRange((y1 + y2), Math.max(x1, x2));
    }

    private static int attach(int y1, int x1, int y2, int x2) {
        if(isOk(y1, x1, y2, x2) || isOk(x1, y1, y2, x2) || isOk(y1, x1, x2, y2) || isOk(x1, y1, x2, y2)) {
            return y1 * x1 + y2 * x2;
        }

        return 0;
    }

    private static int solve() {
        int res = 0;
        for (int i = 0; i < N; i++) {
            int y1 = sticker[i][0];
            int x1 = sticker[i][1];
            for (int j = i + 1; j < N; j++) {
                int y2 = sticker[j][0];
                int x2 = sticker[j][1];
                res = Math.max(res, attach(y1, x1, y2, x2));
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        sticker = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sticker[i][0] = Integer.parseInt(st.nextToken());
            sticker[i][1] = Integer.parseInt(st.nextToken());
        }

        picked = new int[2];
        System.out.println(solve());
    }
}
