package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 로마숫자_만들기_16922 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static final int[] rome = {1, 5, 10, 50};
    private static Set<Integer> res;

    private static void solve(int start, int index, int currSum) {
        if(index == N) {
            res.add(currSum);
            return;
        }

        for (int i = start; i < rome.length; i++) {
            solve(i, index + 1, currSum + rome[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        res = new HashSet<>();
        solve(0, 0, 0);
        System.out.println(res.size());
    }
}
