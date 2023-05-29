package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class FindMinimumCost2_11779 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static List<List<Node>> graph;
    private static int start;
    private static int end;
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
        int[] parent = new int[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        pq.add(new Node(start, 0));
        dist[start] = 0;
        parent[start] = -1;

        while (!pq.isEmpty()) {
            Node u = pq.poll();
            int here = u.node;
            int cost = u.cost;

            if(dist[here] < cost) continue;

            for(Node next : graph.get(here)) {
                if(dist[next.node] > dist[here] + next.cost) {
                    dist[next.node] = dist[here] + next.cost;
                    parent[next.node] = here;
                    pq.add(new Node(next.node, dist[next.node]));
                }
            }
        }

        System.out.println(dist[end]);
        Stack<Integer> res = new Stack<>();
        int x = end;
        while (x != -1) {
            res.add(x);
            x = parent[x];
        }

        System.out.println(res.size());
        while (!res.isEmpty()) {
            System.out.print(res.pop() + " ");
        }
        System.out.println();
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
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        solve();
    }
}
