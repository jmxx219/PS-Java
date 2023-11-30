package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 회장뽑기_2660 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] edge;
    private static final int INF = 987654321;

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(edge[i][k] == INF || edge[k][j] == INF) continue;
                    edge[i][j] = Math.min(edge[i][j], edge[i][k] + edge[k][j]);
                }
            }
        }
    }

    private static void solve() {
        floyd();

        int minScore = INF;
        int[] score = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(i == j || edge[i][j] == INF) continue;
                score[i] = Math.max(score[i], edge[i][j]);
            }
            minScore = Math.min(minScore, score[i]);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if(score[i] == minScore) {
                res.add(i);
            }
        }

        System.out.println(minScore + " " + res.size());
        res.stream().forEach(s -> System.out.print(s + " "));

    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        edge = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(i != j) edge[i][j] = INF;
            }
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == -1 && b == -1) break;
            edge[a][b] = edge[b][a] = 1;
        }

        solve();

    }
}
