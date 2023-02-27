package Algorithm;

import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra { // 가중치가 있는 최단 경로
    public static int N; // 정점의 수
    public static int V; // 간선의 수
    public static List<List<Point>> adj;
    public static int[] dist;
    public static final int init = 987654321;

    public static class Point implements Comparable<Point>{
        public int node;
        public int cost;

        Point(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.cost - o.cost;
        }
    }

    public static void solve(int start) {
        PriorityQueue<Point> pq = new PriorityQueue<Point>();
        dist[start] = 0;
        pq.offer(new Point(start,0));

        while(!pq.isEmpty()){
            Point tmp = pq.poll();
            int cost = tmp.cost;
            int here = tmp.node;

            if(dist[here] < cost) continue;

            for(var next : adj.get(here)) {
                if(next.cost != 0) {
                    int nextDist = next.cost + cost;
                    if(nextDist < dist[next.node]) {
                        dist[next.node] = nextDist;
                        pq.offer(new Point(next.node, nextDist));
                    }
                }
            }
        }
        for(int i = 1; i<=N; i++) {
            if(dist[i] == init) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }
}
