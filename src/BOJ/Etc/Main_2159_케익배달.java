package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N명의 고객에게 순서대로 배달
 * 배달 -> 고객의 위치 또는 고객의 상하좌우 인접 격자점까지
 * 선아의 최단거리 계산
 */
public class Main_2159_케익배달 {
	static final int H = 100_000;
	static final int W = 100_000;
	static int N;
	static Point[] customers;
	static final int[][] DIR = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	private static boolean inRange(int y, int x) {
		return 0 <= y && y <= H && 0 <= x && x <= W;
	}

	private static long calDist(int y, int x, int ny, int nx) {
		return Math.abs(y - ny) + Math.abs(x - nx);
	}

	private static long solve() {
		long[][] dp = new long[N + 1][5];

		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], Long.MAX_VALUE);
		}

		dp[0][0] = 0;
		for (int j = 1; j < 5; j++) {
			dp[0][j] = 1;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 5; j++) {
				int y = customers[i].y + DIR[j][0];
				int x = customers[i].x + DIR[j][1];
				if(!inRange(y, x)) continue;

				for (int k = 0; k < 5; k++) {
					int ny = customers[i - 1].y + DIR[k][0];
					int nx = customers[i - 1].x + DIR[k][1];
					if(!inRange(ny, nx)) continue;

					dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + calDist(y, x, ny, nx));
				}
			}
		}

		long res = Long.MAX_VALUE;
		for (int j = 0; j < 5; j++) {
			if(res > dp[N][j]) res = dp[N][j];
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		customers = new Point[N + 1];
		for (int i = 0; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			customers[i] = new Point(y, x);
		}

		System.out.println(solve());
	}

	static class Point {
		int y, x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}

