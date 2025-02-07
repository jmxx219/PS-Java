package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_5972_택배배송 {
	static class Node implements Comparable<Node> {
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
	static int N;
	static int M;
	static List<List<Node>> graph;

	private static int dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, -1);

		pq.add(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node here = pq.poll();

			if(here.cost > dist[here.node]) continue;

			for(Node next : graph.get(here.node)) {
				if(dist[next.node] == -1 || dist[next.node] > dist[here.node] + next.cost) {
					dist[next.node] = dist[here.node] + next.cost;
					pq.add(new Node(next.node, dist[next.node]));
				}
			}
		}

		return dist[N];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}
		System.out.println(dijkstra(1));
	}
}
