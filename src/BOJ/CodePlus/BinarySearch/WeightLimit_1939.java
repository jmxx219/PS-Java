package BOJ.CodePlus.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WeightLimit_1939 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int start;
    private static int end;
    private static List<List<Edge>> bridge;

    public static class Edge {
        public int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static boolean dfs(int node, int limit, boolean[] visited) {
        if(node == end) return true;

        visited[node] = true;
        for(Edge e : bridge.get(node)) {
            if(!visited[e.to] && e.cost >= limit) {
                if(dfs(e.to, limit, visited)) return true;
            }
        }

        return false;
    }

    public static int solve() {
        int res = 0;

        int left = 1;
        int right = 1000000000;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(dfs(start, mid, new boolean[N + 1])) {
                res = mid;
                left = mid + 1;
            }
            else right = mid - 1;
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bridge = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            bridge.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            bridge.get(from).add(new Edge(to, cost));
            bridge.get(to).add(new Edge(from, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(solve());
    }

}
