package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2169_로봇_조종하기 {
	static int N;
	static int M;
	static int[][] loc;

	private static int solve() {
		int[][] dp = new int[N + 1][M + 1];

		for (int j = 1; j <= M; j++) {
			dp[1][j] = dp[1][j - 1] + loc[1][j];
		}

		for (int i = 2; i <= N; i++) {
			int[] L = new int[M + 1];
			int[] R = new int[M + 1];

			L[1] = dp[i - 1][1] + loc[i][1];
			for (int j = 2; j <= M; j++) {
				L[j] = Math.max(dp[i - 1][j], L[j - 1]) + loc[i][j];
			}

			R[M] = dp[i - 1][M] + loc[i][M];
			for (int j = M - 1; j >= 1; j--) {
				R[j] = Math.max(dp[i - 1][j], R[j + 1]) + loc[i][j];
			}

			for (int j = 1; j <= M; j++) {
				dp[i][j] = Math.max(L[j], R[j]);
			}

		}
		return dp[N][M];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		loc = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				loc[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solve());
	}
}
