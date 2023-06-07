package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class MakeRomanNumber_16922 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static Set<Integer> set;
    private static final int[] roman = {1, 5, 10, 50};

    private static void solve(int start, int index, int sum) {
        if(index == N) {
            set.add(sum);
            return;
        }

        for (int i = start; i < 4; i++) {
            solve(i, index + 1, sum + roman[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        set = new HashSet<>();
        solve(0, 0, 0);
        System.out.println(set.size());
    }
}
