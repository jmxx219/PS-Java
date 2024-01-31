package BOJ.Retry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 이분그래프_1707 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static List<List<Integer>> graph;
    private static int V;
    private static int E;

    private static boolean dfs(int u, int g, int[] group) {
        group[u] = g;
        for(int v : graph.get(u)) {
            if(group[u] == group[v]) return false;
            if(group[v] == -1 && !dfs(v, 1 - g, group)) return false;
        }
        return true;
    }

    private static boolean isBinaryGraph() {
        int[] group = new int[V + 1];
        Arrays.fill(group, -1);

        for(int v = 1; v <= V; v++) {
            if(group[v] != -1) continue;
            if(!dfs(v, 0, group)) return false;
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        while(K-- > 0) {
            graph = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            for(int i = 0; i <= V; i++) graph.add(new ArrayList<>());
            for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            if(isBinaryGraph()) System.out.println("YES");
            else System.out.println("NO");
        }
    }

}