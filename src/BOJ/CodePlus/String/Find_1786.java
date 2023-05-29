package BOJ.CodePlus.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Find_1786 {
    private static BufferedReader br;
    private static char[] T;
    private static char[] P;

    public static int[] makeTable() {
        int[] pi = new int[P.length];

        int begin = 1, matched = 0;
        while (begin + matched < P.length) {
            if(P[begin + matched] == P[matched]) {
                matched += 1;
                pi[begin + matched - 1] = matched;
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

    public static List<Integer> kmp() {
        List<Integer> res = new ArrayList<>();

        int[] pi = makeTable();
        int begin = 0, matched = 0;
        while (begin <= T.length - P.length) {
            if(matched < P.length && T[begin + matched] == P[matched]) {
                matched += 1;
                if(matched == P.length) res.add(begin);
            }
            else {
                if(matched == 0) begin += 1;
                else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = br.readLine().toCharArray();
        P = br.readLine().toCharArray();

        List<Integer> res = kmp();
        System.out.println(res.size());
        res.stream().forEach(i -> System.out.print((i+1) + " "));
    }
}
