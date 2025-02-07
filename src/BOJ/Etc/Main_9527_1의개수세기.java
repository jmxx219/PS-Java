package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main_9527_1의개수세기 {
	static long A;
	static long B;
	static int len;
	static long[] dp;

	private static void init() {
		dp[0] = 1;
		for (int i = 1; i <= len; i++) {
			// dp[i] = (dp[i - 1] << 1) + (1L << i);
			dp[i] = dp[i - 1] * 2 + (long) Math.pow(2, i);
		}
	}

	private static long countBitOne(long n) {
		long cnt = n & 1;
		for (int i = len; i > 0; i--) {
			if((n & (1L << i)) > 0L) {
				cnt += dp[i - 1] + (n - (1L << i) + 1);
				n -= (1L << i);
			}
		}
		return cnt;
	}

	private static long solve() {
		BigInteger bigNumber = BigInteger.valueOf(10).pow(16);
		len = bigNumber.toString(2).length();
		dp = new long[len + 1];
		init();
		return countBitOne(B) - countBitOne(A - 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		System.out.println(solve());
	}
}
