package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 플로이드 와샬: 164ms
 */
public class Main_10159_저울 {
	static int N;
	static int M;
	static boolean[][] graph;

	public static int[] findUnknownItem() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(graph[i][k] && graph[k][j]) graph[i][j] = true;
				}
			}
		}

		int[] res = new int[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == j) continue;
				if(!graph[i][j] && !graph[j][i]) res[i]++;
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		graph = new boolean[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			graph[u][v] = true;
		}

		StringBuilder sb = new StringBuilder();
		for(int x : findUnknownItem()) sb.append(x).append("\n");
		System.out.println(sb);
	}

}
