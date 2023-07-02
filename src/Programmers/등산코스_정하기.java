package Programmers;

// F
import java.util.*;

class 등산코스_정하기 {
    private List<List<Node>> graph;
    private static final int INF = Integer.MAX_VALUE;

    public class Node implements Comparable<Node> {
        int node;
        int cost;
        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    private static boolean isSummit(int node, int[] summits) {
        for (int summit : summits) {
            if (node == summit) return true;
        }
        return false;
    }

    private int[] dijkstra(int[] gates, int[] summits) {
        int[] intensity = new int[graph.size() + 1];
        Arrays.fill(intensity, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int gate : gates) {
            pq.offer(new Node(gate, 0));
            intensity[gate] = 0;
        }

        while(!pq.isEmpty()) {
            Node here = pq.poll();

            if(isSummit(here.node, summits)) continue;
            if (intensity[here.node] < here.cost) continue;

            for(Node next : graph.get(here.node)) {
                int nextCost = Math.max(next.cost, intensity[here.node]);
                if(intensity[next.node] > nextCost) {
                    intensity[next.node] = nextCost;
                    pq.offer(new Node(next.node, nextCost));
                }
            }
        }

        Arrays.sort(summits);
        int[] answer = {-1, -1};
        for(int summit : summits) {
            if(answer[0] == -1 || answer[1] > intensity[summit]) {
                answer[0] = summit;
                answer[1] = intensity[summit];
            }
        }

        return answer;
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] path : paths) {
            graph.get(path[0]).add(new Node(path[1], path[2]));
            graph.get(path[1]).add(new Node(path[0], path[2]));
        }

        return dijkstra(gates, summits);
    }
}