package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ConnectedComponent_11724 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static List<List<Integer>> graph;

    private static void dfs(int u, boolean[] visited) {
        visited[u] = true;
        for(int v : graph.get(u)) {
            if(!visited[v]) {
                dfs(v, visited);
            }
        }
    }

    private static int solve() {
        int cnt = 0;

        boolean[] visited = new boolean[N + 1];
        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                dfs(i, visited);
                cnt += 1;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        System.out.println(solve());
    }
}
