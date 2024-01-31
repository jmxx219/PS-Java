package BOJ.Etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 우주신과의_교감_1774 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static PriorityQueue<Edge> edges;
    private static int[] parent;
    private static int[][] nodes;
    private static boolean[] check;

    static class Edge implements Comparable<Edge> {
        int u, v;
        double dist;
        public Edge(int u, int v, double dist) {
            super();
            this.u = u;
            this.v = v;
            this.dist = dist;
        }
        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    private static double calcDist(int u, int v) {
        int uY = nodes[u][0];
        int uX = nodes[u][1];
        int vY = nodes[v][0];
        int vX = nodes[v][1];
        return Math.sqrt((double)(uY - vY) * (uY - vY) + (double)(uX - vX) * (uX - vX));
    }

    private static int findParent(int x) {
        if(x == parent[x]) return x;
        parent[x] = findParent(parent[x]);
        return parent[x];
    }

    private static void union(int u, int v, int uP, int vP) {
        if(u < v) parent[vP] = uP;
        else parent[uP] = vP;
    }

    private static boolean all() {
        for(int i = 0; i < N; i++) {
            if(!check[i]) return false;
        }
        return true;
    }

    private static double mst() {
        double dist = 0;

        while(!edges.isEmpty()) {
            Edge e = edges.poll();
            int uP = findParent(e.u);
            int vP = findParent(e.v);
            if(uP != vP) {
                dist += e.dist;
                union(e.u, e.v, uP, vP);
            }
            if(all()) break;
        }

        return dist;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        edges = new PriorityQueue<>();
        parent = new int[N];
        nodes = new int[N][2];
        check = new boolean[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes[i][0] = y;
            nodes[i][1] = x;

            for(int j = 0; j < i; j++) {
                edges.add(new Edge(i, j, calcDist(i, j)));
            }
        }

        for(int i = 0 ; i < N; i++) parent[i] = i;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            union(u, v, findParent(u), findParent(v));
            check[u] = check[v] = true;
        }
        System.out.println(String.format("%.2f", mst()));

    }

}
