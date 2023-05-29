package BOJ.CodePlus.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Cubeditor_1701 {
    private static BufferedReader br;
    private static char[] S;

    private static int[] getPartialMatch(char[] P) {
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

        return pi;
    }

    private static int solve() {
        int res = 0;
        String p = "";

        for (int i = S.length - 1; i >= 0 ; i--) {
            p = S[i] + p;
            int[] pi = getPartialMatch(p.toCharArray());
            int longP = Arrays.stream(pi).max().getAsInt();
            if(res < longP) res = longP;
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().toCharArray();
        System.out.println(solve());
    }
}
