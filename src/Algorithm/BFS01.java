package Algorithm;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 0-1 BFS
 */
public class BFS01 {
	static class Edge {
		int node;
		int weight;

		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}
	static int V;
	static int E;
	static List<List<Edge>> graph;
	static final int INF = 987654321;

	private static int[] bfs(int start) {
		Deque<Integer> deque = new LinkedList<>();
		int[] dist = new int[V];
		for (int i = 0; i < V; i++) {
			dist[i] = INF;
		}

		deque.addFirst(start);
		dist[start] = 0;

		while (!deque.isEmpty()) {
			int here = deque.pollFirst();

			for(Edge edge : graph.get(here)) {
				int next = edge.node;
				int weight = edge.weight;

				if(dist[next] > dist[here] + weight) {
					dist[next] = dist[here] + weight;
					if(weight == 0) deque.addFirst(next);
					else deque.addLast(next);
				}
			}
		}

		return dist;
	}

	public static void main(String[] args) {
		V = 6;
		E = 7;
		List<int[]> edges = List.of(new int[]{0, 1, 1}, new int[]{1, 2, 1},
			new int[]{2, 3, 1}, new int[]{3, 4, 1},
			new int[]{4, 5, 0}, new int[]{5, 0, 0},
			new int[]{1, 4, 1});

		graph = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			graph.add(new LinkedList<>());
		}

		for (int[] edge : edges) {
			graph.get(edge[0]).add(new Edge(edge[1], edge[2]));
			graph.get(edge[1]).add(new Edge(edge[0], edge[2]));
		}

		int[] dist = bfs(0);
		for (int i = 0; i < V; i++) {
			System.out.print(dist[i] + " ");
		}
	}

}
