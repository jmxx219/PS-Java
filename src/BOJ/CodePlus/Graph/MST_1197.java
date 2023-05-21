package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MST_1197 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int V;
    private static int E;
    private static List<Edge> edges;
    private static int[] parent;

    private static class Edge implements Comparable<Edge> {
        int u, v;
        int cost;

        public Edge(int from, int to, int cost) {
            this.u = from;
            this.v = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static void union(int u, int v, int uP, int vP) {
        if(uP < vP) parent[vP] = uP;
        else parent[uP] = vP;
    }

    private static int findParent(int x) {
        if(parent[x] == x) return x;
        parent[x] = findParent(parent[x]);
        return parent[x];
    }

    private static int mst() {
        int resCost = 0;
        Collections.sort(edges);

        for(Edge e : edges) {
            int uP = findParent(e.u);
            int vP = findParent(e.v);
            if(uP != vP) {
                resCost += e.cost;
                union(e.u, e.v, uP, vP);
            }
        }

        return resCost;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, cost));
        }

        parent = new int[V + 1];
        for (int i = 1; i < V; i++) {
            parent[i] = i;
        }

        System.out.println(mst());
    }
}
