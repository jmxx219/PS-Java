package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * dfs: 164ms
 * 불가능한 물건의 개수 = (모든 물건의 개수) - (비교 가능한 물건의 개수)
 */
public class Main_10159_저울2 {
	static int N;
	static int M;
	static List<List<Integer>> graph;
	static int[] count;

	private static void dfs(int curr, int root, boolean[] visit) {
		visit[curr] = true;
		count[curr]++;

		for (int next : graph.get(curr)) {
			if(visit[next]) continue;
			count[root]++;
			visit[next] = true;
			dfs(next, root, visit);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) graph.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			graph.get(u).add(v);
		}

		count = new int[N];
		for (int i = 0; i < N; i++) {
			dfs(i, i, new boolean[N]);
		}

		StringBuilder sb = new StringBuilder();
		for(int c : count) sb.append(N - c).append("\n");
		System.out.println(sb);
	}
}
