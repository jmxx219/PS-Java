package Algorithm;

import java.util.Arrays;
import java.util.List;

// 벨만 포드: 최단 경로(가중치 음수인 경우에 사용)
// O(V^3)
public class Bellman_Ford {
    private static int V; // 노드
    private static int E; // 간선
    private static List<Edge> edges;
    private static final long INF = 100000000;

    private static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int cost;

        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void bellmanFord(int start) {
        long[] dist = new long[V + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        for (int i = 1; i < V; i++) { // i : 간선 사용 개수
            for(Edge e : edges) {
                if(dist[e.u] != INF && dist[e.v] > dist[e.u] + e.cost) {
                    dist[e.v] = dist[e.u] + e.cost;
                }
            }
        }

        boolean negative_cycle = false;
        for(Edge e : edges) {
            if(dist[e.u] != INF && dist[e.v] > dist[e.u] + e.cost) {
                negative_cycle = true;
                break;
            }
        }

        if (negative_cycle) {
            System.out.println(-1);
        }
        else {
            for (int i = 1; i <= V; i++) {
                if (i == start) continue;
                if (dist[i] == INF) dist[i] = -1;
                System.out.println(dist[i]);
            }
        }
    }
}
