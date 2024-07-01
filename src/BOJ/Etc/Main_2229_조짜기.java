package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 조가 잘 짜여진 정도의 최댓값
 * 조가 잘 짜여진 정도: 가장 점수가 높은 학생의 점수 - 가장 점수가 낮은 학생의 점수
 * 전체적으로 조가 잘 짜여진 정도: 각각의 조가 잘 짜여진 정도의 합
 * 1명인 경우, 조의 잘 짜여진 정도: 0
 */
public class Main_2229_조짜기 {
	static int N;
	static int[] scores;

	private static int findMaxDiff() {
		int[][] diff = new int[N][N];
		int[] dp =  new int[N];

		for (int i = 0; i < N; i++) {
			int min_n = scores[i];
			int max_n = scores[i];
			for (int j = i + 1; j < N; j++) {
				if(min_n > scores[j]) min_n = scores[j];
				if(max_n < scores[j]) max_n = scores[j];
				diff[i][j] = max_n - min_n;
			}
		}

		for(int i = 1; i < N; i++) {
			dp[i] = diff[0][i];
			for (int j = 0; j < i; j++) {
				dp[i] = Math.max(dp[i], dp[j] + diff[j + 1][i]);
			}
		}

		return dp[N - 1];
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		scores = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(findMaxDiff());
	}
}
