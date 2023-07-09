package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BuyIceCream_2422 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static List<Set<Integer>> noMixs;
    private static int[] picked;
    private static int res;
    private static final int CNT = 3;

    private static void solve(int start, int index, boolean[] check) {
        if(index == CNT) {
            res += 1;
            return;
        }

        for (int i = start; i <= N; i++) {
            if(!check[i]) {
                if(index > 0 && noMixs.get(picked[index - 1]).contains(i)) continue;
                if(index == CNT - 1 && noMixs.get(picked[0]).contains(i)) continue;

                check[i] = true;
                picked[index] = i;
                solve(i + 1, index + 1, check);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        noMixs = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            noMixs.add(new HashSet<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            noMixs.get(a).add(b);
            noMixs.get(b).add(a);
        }

        if (N < 3) {
            System.out.println(0);
        }
        else {
            res = 0;
            picked = new int[CNT];
            solve(1, 0, new boolean[N + 1]);
            System.out.println(res);
        }
    }
}
