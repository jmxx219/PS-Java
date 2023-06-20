package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class CampPreparation_16938 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int L;
    private static int R;
    private static int X;
    private static int[] level;
    private static int[] picked;
    private static int res;

    private static boolean isOk(int index) {
        if(picked[index - 1] - picked[0] < X) return false;

        int tmpSum = 0;
        for (int i = 0; i < index; i++) {
            tmpSum += picked[i];
        }

        return L <= tmpSum && tmpSum <= R;
    }

    private static void solve(int start, int index, boolean[] check) {
        if(index > 1) {
            if(isOk(index)) res += 1;
            if(index == N) return;
        }

        for (int i = start; i < N; i++) {
            if(!check[i]) {
                check[i] = true;
                picked[index] = level[i];
                solve(i + 1, index + 1, check);
                check[i] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        level = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            level[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(level);

        res = 0;
        picked = new int[N];
        solve(0, 0, new boolean[N]);
        System.out.println(res);
    }
}
