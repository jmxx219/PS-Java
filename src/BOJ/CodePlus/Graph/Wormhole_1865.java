package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Wormhole_1865 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int N; // 지점의 수
    private static int M; // 도로의 수
    private static int W; // 웜홀의 수
    private static List<Edge> edges;
    private static final int INF = 987654321;

    private static class Edge {
        int u, v;
        int t;

        public Edge(int s, int e, int t) {
            this.u = s;
            this.v = e;
            this.t = t;
        }
    }

    public static boolean negativeCycle(int S) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        dist[S] = 0;
        for (int i = 1; i < N; i++) {
            for(Edge e : edges) {
                if(dist[e.v] > dist[e.u] + e.t) {
                    dist[e.v] = dist[e.u] + e.t;
                }
            }
        }

        boolean negative_cycle = false;
        for(Edge e : edges) {
            if(dist[e.v] > dist[e.u] + e.t) {
                negative_cycle = true;
                break;
            }
        }

        return negative_cycle;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, t));
                edges.add(new Edge(e, s, t));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken()) * - 1;
                edges.add(new Edge(s, e, t));
            }

            if (negativeCycle(1)) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
