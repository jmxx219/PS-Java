package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_2668_숫자고르기 {
	static int N;
	static int[] nums;

	private static void dfs(int curr, int target, boolean[] visited, List<Integer> res) {
		int next = nums[curr];
		if(!visited[next]) {
			visited[next] = true;
			dfs(next, target, visited, res);
			visited[next] = false;
		}
		if(next == target) res.add(target);
	}

	private static List<Integer> solve() {
		List<Integer> res = new ArrayList<>();

		boolean[] visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			visited[i] = true;
			dfs(i, i, visited, res);
			visited[i] = false;
		}

		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		nums = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			nums[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> res = solve();
		Collections.sort(res);
		StringBuilder sb = new StringBuilder();
		sb.append(res.size()).append("\n");
		for(int x : res) sb.append(x).append("\n");
		System.out.println(sb);
	}
}
