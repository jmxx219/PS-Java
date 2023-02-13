package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 이분 그래프
public class BipartiteGraph_1707 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int K;
    private static int V;
    private static int E;
    private static List<List<Integer>> graph;

    private static void dfs(int u, int c, int[] color) {
        color[u] = c;
        for(int v : graph.get(u)) {
            if(color[v] == 0) {
                dfs(v, 3 - c, color);
            }
        }
    }

    private static boolean solve() {
        int[] color = new int[V + 1]; // 0 - 방문 x, 1 - 방문했으면서 집합이 1, 2 - 방문했으면서 집합이 2

        for(int i = 1; i <= V; i++) {
            if(color[i] == 0) {
                dfs(i, 1, color);
            }
        }

        for(int u = 1; u <= V; u++) { // 인접 노드와 같은 집합일 경우, 이분 그래프 x
            for(int v : graph.get(u)) {
                if(color[u] == color[v]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            if (solve()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
