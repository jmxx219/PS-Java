package Programmers.Etc;

public class 연속_펄스_부분_수열의_합 {
	static int N;
	static int[] A;
	static int[] B;

	private long calcMaximumSum(int[] seq) {
		long[] dp = new long[N];
		dp[0] = seq[0];
		long maxSum = seq[0];

		for (int i = 1; i < N; i++) {
			dp[i] = Math.max(seq[i], dp[i - 1] + seq[i]);
			maxSum = Math.max(maxSum, dp[i]);
		}

		return maxSum;
	}

	private long solve() {
		long aMaxSum = calcMaximumSum(A);
		long bMaxSum = calcMaximumSum(B);
		return Math.max(aMaxSum, bMaxSum);
	}

	public long solution(int[] sequence) {
		N = sequence.length;
		A = new int[N];
		B = new int[N];

		int pulse = 1;
		for (int i = 0; i < N; i++) {
			A[i] = sequence[i] * pulse;
			B[i] = sequence[i] * pulse * -1;
			pulse *= -1;
		}

		return solve();
	}
}
