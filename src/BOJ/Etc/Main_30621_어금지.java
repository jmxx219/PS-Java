package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_30621_어금지 {
	static int N;
	static int[] t;
	static int[] b;
	static int[] c;

	// private static int lowerBound(int i, int prev) {
	// 	int j = i - 1;
	// 	while (j >= 0) {
	// 		if(prev > t[j]) return j;
	// 		j--;
	// 	}
	// 	return -1;
	// }

	private static int lowerBound(int prev) {
		int left = 0;
		int right = N - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if(prev > t[mid]) left = mid + 1;
			else right = mid - 1;
		}
		return right;
	}

	private static long solve() {
		long[] dp = new long[N];

		dp[0] = c[0];
		for (int i = 1; i < N; i++) {
			int prev = t[i] - b[i];
			dp[i] = Math.max(dp[i - 1], c[i]);
			int j = lowerBound(prev);
			if(j >= 0) dp[i] = Math.max(dp[i - 1], c[i] + dp[j]);
		}
		return dp[N - 1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		t = new int[N];
		b = new int[N];
		c = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) t[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) b[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) c[i] = Integer.parseInt(st.nextToken());

		System.out.println(solve());
	}
}
