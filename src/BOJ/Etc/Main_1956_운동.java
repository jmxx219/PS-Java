package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1956_운동 {
	static int V;
	static int E;
	static int[][] edge;
	static final int INF = Integer.MAX_VALUE;

	private static int solve() {
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					if(edge[i][k] == INF || edge[k][j] == INF) continue;
					edge[i][j] = Math.min(edge[i][j], edge[i][k] + edge[k][j]);
				}
			}
		}

		int ans = INF;
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if(i == j) continue;
				if(edge[i][j] != INF && edge[j][i] != INF) {
					ans = Math.min(ans, edge[i][j] + edge[j][i]);
				}
			}
		}

		return ans == INF ? -1 : ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		edge = new int[V + 1][V + 1];
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if(i != j) edge[i][j] = INF;
			}
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edge[a][b] = c;
		}
		System.out.println(solve());
	}
}
