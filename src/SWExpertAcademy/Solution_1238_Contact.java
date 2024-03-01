package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1238_Contact {
	static final int N = 100;
	static int start;
	static boolean[][] graph;
	
	private static int solve() {
		Queue<Integer> queue = new LinkedList<>();
		int[] dist = new int[N + 1];
		
		queue.add(start);
		dist[start] = 1;
		
		while(!queue.isEmpty()) {
			int here = queue.poll();
			for(int next = 1; next <= N; next++) {
				if(graph[here][next] && dist[next] == 0) {
					dist[next] = dist[here] + 1;
					queue.add(next);
				}
			}
		}
		
		int num = start, time = 1;
		for(int i = 1; i <= N; i++) {
			if(dist[i] >= time) {
				time = dist[i];
				num = i;
			}
		}
		
		return num;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 10;
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			graph = new boolean[N + 1][N + 1];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M / 2; i++) {
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				graph[u][v] = true;
			}
			
			sb.append("#").append(t).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);
	}
}
