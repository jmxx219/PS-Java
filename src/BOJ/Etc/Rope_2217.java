package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Rope_2217 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static List<Integer> weight;

    public static int solve() {
        weight.sort(Comparator.reverseOrder());
        int res = 0;
        for (int i = 0; i < N; i++) {
            res = Math.max(res, weight.get(i) * (i + 1));
        }

        return  res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        weight = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            weight.add(Integer.parseInt(st.nextToken()));
        }

        System.out.println(solve());
    }
}
