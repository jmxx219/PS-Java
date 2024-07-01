package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2253_점프 {
	static int N;
	static int M;
	static boolean[] minStone;
	static final int INF = 987654321;
	static int[][] memo;

	private static int jump(int num, int x) {
		if(num == N) return 0;
		if(memo[num][x] != -1) return memo[num][x];

		int cnt = INF;
		for (int nx : new int[] {x - 1, x, x + 1}) {
			if(nx < 1 || num + nx > N || minStone[num + nx]) continue;
			cnt = Math.min(cnt, jump(num + nx, nx) + 1);
		}
		return memo[num][x] = cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minStone = new boolean[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			minStone[Integer.parseInt(st.nextToken())] = true;
		}
		memo = new int[N + 1][(int) Math.sqrt(2 * N) + 1];
		for (int i = 0; i < N; i++) {
			Arrays.fill(memo[i], -1);
		}
		int res = jump(1, 0);
		if(res == INF) System.out.println(-1);
		else System.out.println(res);
		N = 10000;
	}
}
