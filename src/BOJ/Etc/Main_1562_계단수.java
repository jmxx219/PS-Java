package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1562_계단수 {
	static final int MOD = 1_000_000_000;
	static int N;
	static long[][][] memo;

	private static boolean isPossible(int set) {
		return set == (1 << 10) - 1;
	}

	private static long upStairs(int len, int num, int set) {
		if(len == 1) {
			if(!isPossible(set)) return 0;
			return num == 0 ? 0 : 1;
		}
		if(memo[len][num][set] != -1) return memo[len][num][set];

		long res = 0L;
		if(num - 1 >= 0) {
			res = (res + upStairs(len - 1, num - 1, set | (1 << (num - 1)))) % MOD;
		}
		if(num + 1 <= 9) {
			res = (res + upStairs(len - 1, num + 1, set | (1 << (num + 1)))) % MOD;
		}

		memo[len][num][set] = res % MOD;
		return memo[len][num][set];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		memo = new long[N + 1][10][1 << 10];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}

		long res = 0L;
		for(int i = 0; i <= 9; i++) {
			res = (res + upStairs(N, i, 1 << i)) % MOD;
		}
		System.out.println(res);
	}
}
