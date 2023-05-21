package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ShortestPath_1753 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int V;
    private static int E;
    private static List<List<Node>> graph;
    private static int start;
    private static final int INF = 987654321;

    public static class Node implements Comparable<Node>{
        public int node;
        public int cost;

        Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    private static void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[V + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node tmp = pq.poll();
            int here = tmp.node;
            int cost = tmp.cost;

            if(dist[here] < cost) continue;

            for(Node next : graph.get(here)) {
                if(dist[next.node] > dist[here] + next.cost) {
                    dist[next.node] = dist[here] + next.cost;
                    pq.add(new Node(next.node, dist[next.node]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, cost));
        }

        solve();
    }
}
