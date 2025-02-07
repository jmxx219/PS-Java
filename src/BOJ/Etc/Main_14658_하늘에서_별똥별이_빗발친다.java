package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14658_하늘에서_별똥별이_빗발친다 {
	static int N;
	static int M;
	static int L; // 트램펄린 길이
	static int K; // 별똥별 수
	static int[][] star;
	static final int[][] DIR = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	static char[][] map;

	private static boolean inRange(int y, int x, int k) {
		return y <= star[k][0] && star[k][0] <= y + L && x <= star[k][1] && star[k][1] <= x + L;
	}

	private static int solve() {
		int res = 0;
		for (int i = 0; i < K; i++) {
			int y = star[i][0];
			for (int j = 0; j < K; j++) {
				int x = star[j][1];

				int cnt = 0;
				for (int k = 0; k < K; k++) {
					if(inRange(y, x, k)) cnt++;
				}

				res = Math.max(res, cnt);
			}

		}
		return K - res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		star = new int[K][2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			star[i][0] = Integer.parseInt(st.nextToken());
			star[i][1] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solve());
	}
}
