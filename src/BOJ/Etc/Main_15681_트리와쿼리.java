package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15681_트리와쿼리 {
	static int N;
	static int R;
	static int Q;
	static List<List<Integer>> graph;
	static int[] subtree;

	private static int dfs(int root, boolean[] visited) {
		visited[root] = true;

		int cnt = 0;
		for(int next : graph.get(root)) {
			if(visited[next]) continue;
			cnt += dfs(next, visited);
		}
		subtree[root] = cnt + 1;
		return subtree[root];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			graph.get(U).add(V);
			graph.get(V).add(U);
		}

		subtree = new int[N + 1];
		dfs(R, new boolean[N + 1]);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int U = Integer.parseInt(st.nextToken());
			sb.append(subtree[U]).append("\n");
		}
		System.out.println(sb);
	}
}
