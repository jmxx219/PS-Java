package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2252_줄세우기 {
	static int N;
	static int M;
	static List<List<Integer>> graph;
	static int[] indegree;
	
	private static void solve() {
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) queue.add(i);
		}
		
		while(!queue.isEmpty()) {
			int u = queue.poll();
			System.out.print(u + " ");
			for(int v: graph.get(u)) {
				indegree[v] -= 1;
				if(indegree[v] == 0) {
					queue.add(v);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N + 1];
		graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			indegree[v] += 1;
			graph.get(u).add(v);
		}
		solve();
		
	}
}
