package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1717_집합의표현 {
	static int N;
	static int M;
	static int[] parent;
	static int[][] commands;

	private static int findParent(int x) {
		if(parent[x] == x) return x;
		return parent[x] = findParent(parent[x]);
	}

	private static void union(int u, int v) {
		int rootU = findParent(u);
		int rootV = findParent(v);
		if(rootU != rootV) {
			if(u < v) parent[rootV] = rootU;
			else parent[rootU] = rootV;
		}
	}

	private static boolean isSameSet(int u, int v) {
		return findParent(u) == findParent(v);
	}

	private static String solve() {
		StringBuilder sb = new StringBuilder();
		for(int[] command : commands) {
			switch (command[0]) {
				case 0:
					union(command[1], command[2]);
					break;
				case 1:
					boolean isSame = isSameSet(command[1], command[2]);
					sb.append(isSame ? "YES" : "NO").append("\n");
					break;
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}

		commands = new int[M][3];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			commands[i][0] = Integer.parseInt(st.nextToken());
			commands[i][1] = Integer.parseInt(st.nextToken());
			commands[i][2] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solve());
	}
}
