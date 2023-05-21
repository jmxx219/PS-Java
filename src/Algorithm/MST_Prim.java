package Algorithm;

import java.util.List;
import java.util.PriorityQueue;

public class MST_Prim {
    private static int N;
    private static List<List<Edge>> graph;

    private static class Edge implements Comparable<Edge> {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static int prim(int S) {
        int resCost = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];

        for(Edge v : graph.get(S)) pq.add(v);
        visited[S] = true;

        while (!pq.isEmpty()) {
            Edge u = pq.poll();
            if(visited[u.to]) continue;

            visited[u.to] = true;
            resCost += u.cost;
            for(Edge v : graph.get(u.to)) {
                pq.add(v);
            }
        }

        return resCost;
    }
}
