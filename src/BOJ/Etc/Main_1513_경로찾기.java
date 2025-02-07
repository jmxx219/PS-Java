package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1513_경로찾기 {
	static int N;
	static int M;
	static int C;
	static int[][] arcade;
	static final int MOD = 1_000_007;

	private static int[][][][] solve() {
		// (y, x)에서 현재까지 방문한 오락실 개수, 가장 최근 방문한 오락실 번호
		int[][][][] dp = new int[N + 1][M + 1][C + 1][C + 1];

		if(arcade[1][1] == 0) dp[1][1][0][0] = 1;
		else dp[1][1][1][arcade[1][1]] = 1;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(arcade[i][j] == 0) { // 현재 오락실이 아니면
					for (int count = 0; count <= C ; count++) {
						for (int prev = 0; prev <= C; prev++) {
							dp[i][j][count][prev] += dp[i - 1][j][count][prev] + dp[i][j - 1][count][prev];
							dp[i][j][count][prev] %= MOD;
						}
					}
				}
				else {
					for (int count = 1; count <= C ; count++) {
						for (int prev = 0; prev < arcade[i][j]; prev++) {
							dp[i][j][count][arcade[i][j]] += dp[i - 1][j][count - 1][prev] + dp[i][j - 1][count - 1][prev];
							dp[i][j][count][arcade[i][j]] %= MOD;
						}
					}
				}
			}
		}

		return dp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arcade = new int[N + 1][M + 1];
		for (int i = 1; i <= C; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			arcade[y][x] = i;
		}
		int[][][][] dp = solve();
		StringBuilder sb = new StringBuilder();
		for (int count = 0; count <= C; count++) {
			int sum = 0;
			for (int prev = 0; prev <= C; prev++) {
				sum = (sum + dp[N][M][count][prev]) % MOD;
			}
			sb.append(sum).append(" ");
		}
		System.out.println(sb);
	}
}
