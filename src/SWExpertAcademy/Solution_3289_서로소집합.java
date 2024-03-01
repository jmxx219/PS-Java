package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3289_서로소집합 {
	static int N;
	static int M;
	static int[] parent;
	
	public static int find(int a) {
		if(a == parent[a]) return a;
		parent[a] = find(parent[a]);
		return parent[a];
	}
	
	public static int find(int a, int b) {
		// 같은 집합에 속해있다면 1을, 아니면 0
		return find(a) == find(b) ? 1 : 0;
	}
	public static void union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
		if(aP < bP) parent[bP] = aP;
		else parent[aP] = bP;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parent = new int[N];
			for(int i = 0; i < N; i++) parent[i] = i;
			
			sb.append("#").append(t).append(" ");
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				if(c == 0) union(a, b);
				else sb.append(find(a, b));
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
