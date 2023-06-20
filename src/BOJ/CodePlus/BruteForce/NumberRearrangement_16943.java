package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberRearrangement_16943 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static String A;
    private static int B;
    private static int res;

    private static void solve(int start, String pick, boolean[] check) {
        if(pick.length() > 0 && pick.charAt(0) == '0') return;

        if(pick.length() == A.length()) {
            int x = Integer.parseInt(pick);
            if(x <= B && x > res) res = x;
            return;
        }

        for (int i = 0; i < A.length(); i++) {
            if(!check[i]) {
                check[i] = true;
                solve(i + 1, pick + A.charAt(i), check);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        A = st.nextToken();
        B = Integer.parseInt(st.nextToken());

        res = -1;
        solve(0, "", new boolean[A.length()]);
        System.out.println(res);
    }
}
