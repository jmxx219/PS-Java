package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 	solve(col, idx): 현재 col에서 시작해서 idx 이름을 적었을 때의 제곱 합의 최소값
 * 	1) 현재 줄에서 이어 쓰는 경우
 * 	 - 단, M 길이를 넘지 않는다면 현재 col 뒤에 idx 이름을 넣을 수 있음
 * 	2) 다음 줄에 쓰는 경우
 * 	 - (현재 col까지의 제곱 합의 최소값) + idx 이름을 다음 줄에 넣는 경우
 */
public class Main_2281_데스노트 {
	static int N;
	static int M;
	static int[] name;
	static int[][] memo;

	private static int solve(int col, int idx) {
		if(idx == N) return 0;
		if(memo[col][idx] != -1) return memo[col][idx];

		int ret = (M - col) * (M - col) + solve(name[idx], idx + 1);
		if(col + name[idx] + 1 <= M) {
			ret =  Math.min(ret, solve(col + name[idx] + 1, idx + 1));
		}
		return memo[col][idx] = ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		name = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			name[i] = Integer.parseInt(st.nextToken());
		}

		memo = new int[M + 1][N];
		for (int i = 0; i <= M; i++) Arrays.fill(memo[i], -1);
		System.out.println(solve(name[0], 1));
	}
}
