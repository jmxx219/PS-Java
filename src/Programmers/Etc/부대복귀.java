package Programmers.Etc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 16:40 ~ 17:00
public class 부대복귀 {
    // 총지역 수: n
    // 길 정보: reads
    // 부대원 위치: sources
    // 강철부대 지역: destination -> 출발지
    private List<List<Integer>> graph;

    public class Node {
        int node;
        int cost;
        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    private int[] solve(int n, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(start, 0));
        dist[start] = 0;

        while(!queue.isEmpty()) {
            Node tmp = queue.poll();
            int here = tmp.node;
            int cost = tmp.cost;

            if(cost > dist[here]) continue;

            for(int v : graph.get(here)) {
                int nextDist = dist[here] + 1;
                if(dist[v] == -1 || dist[v] > nextDist) {
                    dist[v] = nextDist;
                    queue.add(new Node(v, nextDist));
                }
            }
        }
        return dist;
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        int[] dist = solve(n, destination);

        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
        }
        return answer;
    }
}