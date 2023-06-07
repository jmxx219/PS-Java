package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LicensePlate1_16968 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static char[] form;
    private static int[] picked;
    private static int res;

    private static void solve(int index, int prev) {
        if(index == form.length) {
            res += 1;
            return;
        }

        if(form[index] == 'c') { // 문자
            for (int i = 'a'; i <= 'z'; i++) {
                if(i == prev) continue;
                picked[index] = i;
                solve(index + 1, i);
            }
        }
        else { // 숫자
            for (int i = 0; i < 10; i++) {
                if(i == prev) continue;
                picked[index] = i;
                solve(index + 1, i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        form = br.readLine().toCharArray();

        res = 0;
        picked = new int[form.length];
        solve(0, -1);

        System.out.println(res);
    }
}
