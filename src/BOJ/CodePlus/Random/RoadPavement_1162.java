package BOJ.CodePlus.Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class RoadPavement_1162 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int K;
    private static List<List<Node>> graph;

    public static class Node implements Comparable<Node> {
        int node;
        long cost;
        int k;

        public Node(int node, long cost) {
            this(node, cost, 0);
        }

        public Node(int node, long cost, int k) {
            this.node = node;
            this.cost = cost;
            this.k = k;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static long solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[][] dist = new long[N + 1][K + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        pq.add(new Node(1, 0, 0));
        dist[1][0] = 0;

        while (!pq.isEmpty()) {
            Node here = pq.poll();

            if(dist[here.node][here.k] < here.cost) continue;
            if(here.node == N) break;

            for(Node next : graph.get(here.node)) {
                long nextDist = here.cost;
                if(here.k < K) {
                    if(dist[next.node][here.k + 1] > nextDist) {
                        dist[next.node][here.k + 1] = nextDist;
                        pq.add(new Node(next.node, nextDist, here.k + 1));
                    }
                }
                nextDist = here.cost + next.cost;
                if(dist[next.node][here.k] > nextDist) {
                    dist[next.node][here.k] = nextDist;
                    pq.add(new Node(next.node, nextDist, here.k));
                }
            }
        }

        long res = Long.MAX_VALUE;
        for(int i = 0; i < K + 1; i++) {
            if(res >  dist[N][i]) res = dist[N][i];
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        System.out.println(solve());

    }

}
