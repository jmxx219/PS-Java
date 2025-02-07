package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1613_역사 {
	static int N; // 사건의 개수
	static int K; // 전후 관계의 개수
	static List<List<Integer>> graph;
	static int[][] relations;

	private static void dfs(int start, int curr, boolean[] visited) {
		visited[curr] = true;
		for(int next: graph.get(curr)) {
			if(visited[next]) continue;
			relations[start][next] = -1;
			relations[next][start] = 1;
			dfs(start, next, visited);
		}
	}

	private static void solve() {
		relations = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			dfs(i, i, new boolean[N + 1]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
		}

		solve();

		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			sb.append(relations[u][v]).append("\n");
		}
		System.out.println(sb);
	}
}
