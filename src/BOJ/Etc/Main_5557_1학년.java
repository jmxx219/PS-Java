package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_5557_1학년 {
	static int N;
	static int[] A;
	static int MAX = 20;

	private static long solve() {
		long[][] dp = new long[N - 1][MAX + 1];

		dp[0][A[0]] = 1;

		for (int i = 1; i < N - 1; i++) {
			for (int j = 0; j <= MAX; j++) {
				if(dp[i - 1][j] <= 0) continue;

				if(j + A[i] <= MAX)  {
					dp[i][j + A[i]] += dp[i - 1][j];
				}
				if(j - A[i] >= 0)  {
					dp[i][j - A[i]] += dp[i - 1][j];
				}
			}
		}

		return dp[N - 2][A[N - 1]];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		A = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solve());
	}
}
