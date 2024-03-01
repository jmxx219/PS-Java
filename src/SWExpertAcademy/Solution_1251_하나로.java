package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1251_하나로 {
	static class Edge implements Comparable<Edge>{
		int u, v;
		double cost;
		public Edge(int u, int v, double cost) {
			super();
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}
	}
	
	static int N;
	static double E; //환경 부담 세율
	static double[][] point;
	static int[] parent;
	static List<Edge> edges;
	
	private static double calcDist(int i, int j) {
		return Math.sqrt((point[i][0] - point[j][0]) * (point[i][0] - point[j][0])
				+ (point[i][1] - point[j][1]) * (point[i][1] - point[j][1]));
	}
	
	private static int find(int x) {
		if(x == parent[x]) return x;
		parent[x] = find(parent[x]);
		return parent[x];
	}
	
	
	private static void union(int u, int v2, int uP, int vP) {
		if(uP < vP) parent[vP] = uP;
		else parent[uP] = vP;
	}
	
	private static double solve() {
		parent = new int[N];
		for(int i = 0; i < N; i++) parent[i] = i;
		
		edges = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				double d = calcDist(i, j);
				edges.add(new Edge(i, j, d * d * E));
			}
		}
		Collections.sort(edges);
		
		double res = 0;
		int vCnt = 0;
		for(Edge e : edges) {
			int u = e.u;
			int v = e.v;
			int uP = find(u);
			int vP = find(v);
			if(uP != vP) {
				union(u, v, uP, vP);
				res += e.cost;
				vCnt += 1;
				if(vCnt == N - 1) break;
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			point = new double[N][2];
			for(int j = 0; j < 2; j++) {
				st = new StringTokenizer(br.readLine());
				for(int i = 0; i < N; i++) {
					point[i][j] = Double.parseDouble(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine());
			E = Double.parseDouble(st.nextToken());

			sb.append("#").append(t).append(" ").append(String.format("%.0f", solve())).append("\n");
		}
		System.out.println(sb);

	}
}
