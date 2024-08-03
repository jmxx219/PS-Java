package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * dp[j] = j만큼의 비용으로 확보할 수 있는 메모리의 최대값
 */
public class Main_7579_앱 {
	static int N;
	static int M;
	static int[] memory;
	static int[] cost; // 비활성화 비용
	static int totalCost;

	private static int calcMinCost() {
		int[] dp = new int[totalCost + 1];
		Arrays.fill(dp, -1);

		dp[0] = 0;
		for (int i = 0; i < N; i++) {
			for (int j = totalCost; j >= 0; j--) {
				if(j - cost[i] < 0) continue;
				if(dp[j - cost[i]] == -1) continue;
				dp[j] = Math.max(dp[j], dp[j - cost[i]] + memory[i]);
			}
		}

		int c = 0;
		for (; c < totalCost; c++) {
			if(dp[c] >= M) break;
		}
		return c;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		memory = new int[N];
		cost = new int[N];
		totalCost = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			totalCost += cost[i];
		}
		System.out.println(calcMinCost());
	}
}
