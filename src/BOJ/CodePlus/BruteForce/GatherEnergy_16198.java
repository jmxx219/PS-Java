package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GatherEnergy_16198 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static List<Integer> W;
    private static int res = 0;
    private static int[] selected;

    public static int calcEnergy() {
        List<Integer> tempW = new ArrayList<>();
        tempW.addAll(W);

        int sum = 0;
        for (int i = 0; i < selected.length; i++) {
            sum += (tempW.get(selected[i] - 1) * tempW.get(selected[i] + 1));
            tempW.remove(selected[i]);
            for (int j = i + 1; j < selected.length; j++) {
                if(selected[i] < selected[j]) selected[j]--;
            }
        }

        return sum;
    }

    public static void solve(int index, boolean[] visited) {
        if(index == selected.length) {
            res = Math.max(res, calcEnergy());
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            if(!visited[i]) {
                visited[i] = true;
                selected[index] = i;
                solve(index + 1, visited);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        W = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            W.add(Integer.parseInt(st.nextToken()));
        }

        selected = new int[N - 2];
        solve(0, new boolean[N]);
        System.out.println(res);
    }
}
