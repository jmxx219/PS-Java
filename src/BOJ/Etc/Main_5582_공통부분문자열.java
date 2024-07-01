package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_5582_공통부분문자열 {
	static char[] S;
	static char[] P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = st.nextToken().toCharArray();
		st = new StringTokenizer(br.readLine());
		P = st.nextToken().toCharArray();

		int res =0;
		int[][] dp = new int[S.length + 1][P.length + 1];
		for (int i = 1; i <= S.length; i++) {
			for (int j = 1; j <= P.length; j++) {
				if(S[i - 1] == P[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					res = Math.max(res, dp[i][j]);
				}
			}
		}
		System.out.println(res);
	}
}
