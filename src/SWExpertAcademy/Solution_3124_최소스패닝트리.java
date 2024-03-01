package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_3124_최소스패닝트리 {
	static class Edge implements Comparable<Edge>{
		int u, v;
		int cost;
		public Edge(int u, int v, int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	static int V; 
	static int E;
	static List<Edge> edges;
	static int[] parent;
	
	private static int find(int x) {
		if(x == parent[x]) return x;
		parent[x] = find(parent[x]);
		return parent[x];
	}
	
	
	private static void union(int u, int v2, int uP, int vP) {
		if(uP < vP) parent[vP] = uP;
		else parent[uP] = vP;
	}
	
	private static long mst() {
		long res = 0;
		
		Collections.sort(edges);
		
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
				if(vCnt == V - 1) break;
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
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			parent = new int[V];
			for(int i = 0; i < V; i++) parent[i] = i;
			
			edges = new ArrayList<>();
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				int cost = Integer.parseInt(st.nextToken());
				edges.add(new Edge(u, v, cost));
			}

			sb.append("#").append(t).append(" ").append(mst()).append("\n");
		}
		System.out.println(sb);

	}
}
