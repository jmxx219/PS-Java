package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2213_트리의_독립집합 {
	static int N;
	static int[] W;
	static List<List<Integer>> graph;
	static int[][] dp;
	static List<Integer> res;

	private static void trace(int curr, boolean picked, boolean[] visited) {
		visited[curr] = true;

		if(picked) {
			res.add(curr);
			for(int next: graph.get(curr)) {
				if(visited[next]) continue;
				trace(next, false, visited);
			}
		}
		else {
			for(int next: graph.get(curr)) {
				if(visited[next]) continue;
				if(dp[next][0] > dp[next][1]) trace(next, false, visited);
				else trace(next, true, visited);
			}
		}
	}

	private static void dfs(int curr, boolean[] visited) {
		dp[curr][0] = 0;
		dp[curr][1] = W[curr];

		visited[curr] = true;
		for(int next: graph.get(curr)) {
			if(!visited[next]) {
				dfs(next, visited);
				dp[curr][0] += Math.max(dp[next][0], dp[next][1]);
				dp[curr][1] += dp[next][0];
			}
		}
	}

	public static String solve() {
		dfs(1, new boolean[N + 1]);

		// for (int i = 1; i <= N; i++) {
		// 	System.out.println(i + ": " + dp[i][0] + "," + dp[i][1]);
		// }

		StringBuilder sb = new StringBuilder();
		if(dp[1][0] > dp[1][1]) {
			 sb.append(dp[1][0]).append("\n");
			 trace(1, false, new boolean[N + 1]);
		}
		else {
			sb.append(dp[1][1]).append("\n");
			trace(1, true, new boolean[N + 1]);
		}

		Collections.sort(res);
		for(int x : res) sb.append(x).append(" ");

		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		W = new int[N + 1];
		dp = new int[N + 1][2];
		graph = new ArrayList<>();
		res = new ArrayList<>();

		graph.add(new ArrayList<>());
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			W[i] = Integer.parseInt(st.nextToken());
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}

		System.out.println(solve());
	}
}
