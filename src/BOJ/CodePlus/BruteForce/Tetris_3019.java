package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.StringTokenizer;

public class Tetris_3019 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int C;
    private static int P;
    private static int[] H;

    private static int check(int i, String s) {
        if(i + s.length() > C) return 0;

        int base = H[i] - (s.charAt(0) - '0');
        for (int j = 0; j < s.length(); j++) {
            if(base != H[i + j] - (s.charAt(j) - '0')) return 0;
        }

        return 1;
    }

    private static int calc(int i) {
        switch (P) {
            case 1:
                return check(i, "0") + check(i, "0000");
            case 2:
                return check(i, "00");
            case 3:
                return check(i, "001") + check(i, "10");
            case 4:
                return check(i, "100") + check(i, "01");
            case 5:
                return check(i, "000") + check(i, "10") + check(i, "01") + check(i, "101");
            case 6:
                return check(i, "000") + check(i, "00") + check(i, "011") + check(i, "20");
            case 7:
                return check(i, "000") + check(i, "00") + check(i, "110") + check(i, "02");
        }
        return 0;
    }

    private static int solve() {
        int ans = 0;

        for (int i = 0; i < C; i++) {
            ans += calc(i);
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        H = new int[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }
}
