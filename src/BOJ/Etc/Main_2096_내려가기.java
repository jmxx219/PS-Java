package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2096_내려가기 {
	static int N;
	static int[][] scores;

	private static String calcScore() {
		int[][] maxDp = new int[N][3];
		int[][] minDp = new int[N][3];

		maxDp[N - 1][0] = minDp[N - 1][0] =scores[N - 1][0];
		maxDp[N - 1][1] = minDp[N - 1][1] =scores[N - 1][1];
		maxDp[N - 1][2] = minDp[N - 1][2] =scores[N - 1][2];
		for (int i = N - 2; i >= 0 ; i--) {
			maxDp[i][0] = Math.max(maxDp[i + 1][0], maxDp[i + 1][1]) + scores[i][0];
			maxDp[i][1] = Math.max(Math.max(maxDp[i + 1][0], maxDp[i + 1][1]), maxDp[i + 1][2]) + scores[i][1];
			maxDp[i][2] = Math.max(maxDp[i + 1][1], maxDp[i + 1][2]) + scores[i][2];

			minDp[i][0] = Math.min(minDp[i + 1][0], minDp[i + 1][1]) + scores[i][0];
			minDp[i][1] = Math.min(Math.min(minDp[i + 1][0], minDp[i + 1][1]), minDp[i + 1][2]) + scores[i][1];
			minDp[i][2] = Math.min(minDp[i + 1][1], minDp[i + 1][2]) + scores[i][2];
		}

		int maxScore = Math.max(Math.max(maxDp[0][0], maxDp[0][1]), maxDp[0][2]);
		int minScore = Math.min(Math.min(minDp[0][0], minDp[0][1]), minDp[0][2]);
		return maxScore + " " + minScore;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		scores = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			scores[i][0] = Integer.parseInt(st.nextToken());
			scores[i][1] = Integer.parseInt(st.nextToken());
			scores[i][2] = Integer.parseInt(st.nextToken());
		}
		System.out.println(calcScore());
	}
}
