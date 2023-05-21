package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class TimeMachine_11657 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N; // 노드
    private static int M; // 간선
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
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        for (int i = 1; i < N; i++) {
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
            for (int i = 1; i <= N; i++) {
                if (i == start) continue;
                if (dist[i] == INF) dist[i] = -1;
                System.out.println(dist[i]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        bellmanFord(1);
    }
}
