package BOJ.CodePlus.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Promotion_1305 {
    private static BufferedReader br;
    private static int L;
    private static char[] P;

    private static int solve() {
        int[] pi = new int[P.length];
        int begin = 1, matched = 0;

        while (begin + matched < P.length) {
            if(P[begin + matched] == P[matched]) {
                pi[begin + matched] = matched + 1;
                matched += 1;
            }
            else {
                if(matched == 0) begin += 1;
                else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
        return P.length - pi[P.length - 1];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        P = br.readLine().toCharArray();

        System.out.println(solve());
    }
}
