package Algorithm;

import java.util.ArrayList;
import java.util.List;

public class MST_Kruskal {
    private static int V;
    private static List<Edge> edges;

    public static class Edge implements Comparable<Edge>{
        public int u;
        public int v;
        public int cost;

        public Edge(int u, int v, int cost){
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(cost, o.cost);
        }
    }

    private static void union(int u, int v, int[] parent) {
        int uP = find(u, parent);
        int vP = find(v, parent);
        if(uP < vP) parent[vP] = uP;
        else parent[uP] = vP;
    }

    private static int find(int x, int[] parent) {
        if(parent[x] == x) return x;
        parent[x] = find(parent[x], parent);
        return parent[x];
    }

    private static int mst(int[] parent) {
        int res = 0;

        for(Edge edge : edges) {
            int uP = find(edge.u, parent);
            int vP = find(edge.v, parent);
            if(uP != vP) { // 부모 같으면 사이클 형성
                res += edge.cost;
                union(edge.u, edge.v, parent);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        edges = new ArrayList<>();

        int[] parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        mst(parent);
    }

}
