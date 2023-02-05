package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WriteNumber1_1748 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;

    public static int solve() {
        int numLen = String.valueOf(N).length();
        List<Integer> count = new ArrayList<>();

        count.add(0);

        int res = 0;
        for(int i = 1; i <= numLen; i++) {
            count.add(count.get(i - 1) * 10 + 9);
            if(i == numLen) {
                res += (N - count.get(i - 1)) * i;
            }
            else {
                res += (count.get(i) - count.get(i - 1)) * i;
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        System.out.println(solve());
    }
}
