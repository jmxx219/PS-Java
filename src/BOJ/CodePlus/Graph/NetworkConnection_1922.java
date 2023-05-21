package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class NetworkConnection_1922 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
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

    private static int prim() {
        int resCost = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] isVisited = new boolean[N + 1];

        for(Edge v : graph.get(1)) pq.add(v);
        isVisited[1] = true;

        while (!pq.isEmpty()) {
            Edge u = pq.poll();
            if(isVisited[u.to]) continue;

            isVisited[u.to] = true;
            resCost += u.cost;
            for(Edge v : graph.get(u.to)) {
                pq.add(v);
            }
        }

        return resCost;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Edge(to, cost));
            graph.get(to).add(new Edge(from, cost));
        }
        System.out.println(prim());
    }
}
