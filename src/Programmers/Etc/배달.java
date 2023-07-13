package Programmers.Etc;

import java.util.*;

public class 배달 {
    public int[][] town;
    public static final int INF = 987654321;

    public static class Point implements Comparable<Point>{
        public int cost;
        public int here;
        public Point(int here, int cost) {
            this.here = here; // 노드
            this.cost = cost; // 거리
        }
        public int compareTo(Point o) {
            return this.cost - o.cost;
        }
    }

    public int solve(int S, int N, int K) {
        int[] dist = new int[N + 1];
        PriorityQueue<Point> pq = new PriorityQueue<>();
        Arrays.fill(dist, INF);

        pq.add(new Point(S, 0));
        dist[S] = 0;

        while(!pq.isEmpty()) {
            Point node = pq.poll();
            int cost = node.cost;
            int here = node.here;

            if(dist[here] < cost) continue;

            for(int v = 1; v < N + 1; v++) {
                if(town[here][v] == 0) continue;
                int nextDist = dist[here] + town[here][v];
                if(dist[v] == -1 || dist[v] > nextDist) {
                    dist[v] = nextDist;
                    pq.add(new Point(v, nextDist));
                }
            }
        }

        int cnt = 1;
        for(int i = 2; i < N + 1; i++) {
            if(dist[i] <= K) cnt += 1;
        }
        return cnt;
    }

    public int solution(int N, int[][] road, int K) {

        town = new int[N + 1][N + 1];
        for(int i = 0; i < road.length; i++) {
            int u = road[i][0];
            int v = road[i][1];
            if(town[u][v] == 0 || town[u][v] > road[i][2]) {
                town[u][v] = road[i][2];
                town[v][u] = road[i][2];
            }
        }

        return solve(1, N, K);
    }
}
