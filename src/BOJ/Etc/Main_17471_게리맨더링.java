package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17471_게리맨더링 {
	static int N;
	static int[] person;
	static List<List<Integer>> graph;
	static int res;
	
	private static void dfs(int u, int team, int[] group) {
		group[u] = 0;
		for(int v : graph.get(u)) {
			if(group[v] == team) {
				dfs(v, team, group);
			}
		}
	}
	
	private static boolean possible(int[] group) {
		for(int i = 0; i < N; i++) {
			if(group[i] == 1) {
				dfs(i, 1, group);
				break;
			}
		}
		for(int i = 0; i < N; i++) {
			if(group[i] == 2) {
				dfs(i, 2, group);
				break;
			}
		}
		for(int i = 0; i < N; i++) {
			if(group[i] != 0) return false;
		}
		return true;
	}
	
	private static void solve() {
		for(int i = 1; i < (1 << N) - 1; i++) {
			int[] group = new int[N];
			int p1 = 0;
			int p2 = 0;
			for(int j = 0; j < N; j++) {
				if((i & (1 << j)) == 0) {
					group[j] = 1;
					p1 += person[j];
				}
				else {
					group[j] = 2;
					p2 += person[j];
				}
			}
			
			if(possible(group)) {
				int diff = Math.abs(p1 - p2);
				if(res == -1 || res > diff) res = diff;
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		person = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		graph = new ArrayList<>();
		for(int i = 0; i < N; i++) graph.add(new ArrayList<>());
		
		for(int u = 0; u < N; u++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for(int j = 0; j < m; j++) {
				int v = Integer.parseInt(st.nextToken()) - 1;
				graph.get(u).add(v);
				graph.get(v).add(u);
			}
		}
		
		res = -1;
		solve();
		System.out.println(res);
	}
}
