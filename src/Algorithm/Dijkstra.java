package Algorithm;

import java.util.List;
import java.util.PriorityQueue;

// O(ElogE) : Min Heap(PriorityQueue)
// O(ElogV) : BST(TreeSet) -> 최소값, 검색, 값 갱신
public class Dijkstra { // 가중치가 있는 최단 경로
    public static int V; // 정점의 수
    public static int E; // 간선의 수
    public static List<List<Node>> adj;
    public static int[] dist;
    public static final int INF = 987654321;

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

    public static void solve(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        dist[start] = 0;
        pq.offer(new Node(start,0));

        while(!pq.isEmpty()){
            Node tmp = pq.poll();
            int cost = tmp.cost;
            int here = tmp.node;

            if(dist[here] < cost) continue;

            for(var next : adj.get(here)) {
                if(next.cost != 0) {
                    int nextDist = next.cost + cost;
                    if(nextDist < dist[next.node]) {
                        dist[next.node] = nextDist;
                        pq.offer(new Node(next.node, nextDist));
                    }
                }
            }
        }
        for(int i = 1; i<= V; i++) {
            if(dist[i] == INF) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }
}
