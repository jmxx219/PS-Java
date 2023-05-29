package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Floyd2_11780 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] graph;
    private static int[][] via;
    private static final int INF = 987654321;

    private static void floydWarshall() {
        for (int k = 1; k <= N ; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        via[i][j] = k;
                    }
                }
            }
        }
    }

    private static void reconstruct(int u, int v, List<Integer> path) {
        if(via[u][v] == -1) {
            path.add(u);
            if(u != v) path.add(v);
        }
        else {
            int w = via[u][v];
            reconstruct(u, w, path);
            path.remove(path.size() - 1);
            reconstruct(w, v, path);
        }
    }

    private static void solve() {
        via = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(via[i], -1);
        }

        floydWarshall();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] == INF) graph[i][j] = 0;
                sb.append(graph[i][j] + " ");
            }
            sb.append("\n");
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(graph[i][j] == 0) sb.append("0\n");
                else {
                    List<Integer> path = new ArrayList<>();
                    reconstruct(i, j, path);
                    sb.append(path.size() + " ");
                    path.stream().forEach(k -> sb.append(k + " "));
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(i != j) graph[i][j] = INF;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if(graph[u][v] > cost) graph[u][v] = cost;
        }

        solve();
    }
}
