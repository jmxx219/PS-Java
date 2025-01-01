package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2617_구슬찾기 {
	static int N;
	static int M;
	static List<List<Integer>> graph;
	static List<List<Integer>> revGraph;

	private static int dfs(int x, boolean[] visited, List<List<Integer>> graph) {
		visited[x] = true;

		int cnt = 1;
		for(int v : graph.get(x)) {
			if(visited[v]) continue;
			cnt += dfs(v, visited, graph);
		}
		return cnt;
	}

	private static int solve() {
		int res = 0;

		int mid = (N + 1) / 2;
		for (int i = 1; i <= N; i++) {
			int minCnt = dfs(i, new boolean[N + 1], graph) - 1;
			int maxCnt = dfs(i, new boolean[N + 1], revGraph) - 1;
			if(minCnt >= mid || maxCnt >= mid) res++;
		}

		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		revGraph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			revGraph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			revGraph.get(v).add(u);
		}
		System.out.println(solve());
	}
}
