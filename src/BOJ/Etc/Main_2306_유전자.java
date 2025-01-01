package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2306_유전자 {
	static char[] DNA;
	static int N;

	private static boolean isKOI(char l, char r) {
		return (l == 'a' && r == 't') || (l == 'g' && r == 'c');
	}

	private static int solve() {
		int[][] dp = new int[N][N];
		for (int size = 1; size < N; size++) {
			for (int i = 0; i + size < N; i++) {
				int j = i + size;
				if(isKOI(DNA[i], DNA[j])) {
					dp[i][j] = 2 + dp[i + 1][j - 1];
				}
				for (int k = i; k < j; k++) {
					dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j]);
				}
			}
		}
		return dp[0][N - 1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DNA = br.readLine().toCharArray();
		N = DNA.length;
		System.out.println(solve());
	}
}
