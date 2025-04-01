package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2624_동전바꿔주기 {
	static class Coin {
		int p;
		int n;

		public Coin(int p, int n) {
			this.p = p;
			this.n = n;
		}
	}
	static int T;
	static int K;
	static Coin[] coins;

	private static int solve() {
		int[] dp = new int[T + 1];

		dp[0] = 1;
		for(Coin c : coins) {
			int coin = c.p;
			for (int money = T; money >= 0; money--) {
				for (int cnt = 1; cnt <= c.n; cnt++) {
					if(money - coin * cnt >= 0) {
						dp[money] += dp[money - coin * cnt];
					}
				}
			}
		}

		return dp[T];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());

		coins = new Coin[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			coins[i] = new Coin(p, n);
		}
		System.out.println(solve());
	}
}
