package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main_2248_이진수찾기 {
	static int N;
	static int L;
	static long I;

	private static String solve() {
		long[][] dp = new long[N + 1][L + 1];

		dp[0][0] = 1;
		for(int i = 1; i <= N; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= L; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
			}
		}

		StringBuilder result = new StringBuilder();
		for (int pos = N; pos > 0; --pos) {
			int tempPos = pos;
			long count = IntStream.range(0, L + 1).
				mapToLong(k -> dp[tempPos - 1][k]).
				sum();

			if (count < I) {
				result.append(1);
				I -= count;
				L--;
			} else result.append(0);
		}

		return result.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		I = Long.parseLong(st.nextToken());
		System.out.println(solve());
	}
}
