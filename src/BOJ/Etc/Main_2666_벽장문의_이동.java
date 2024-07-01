package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2666_벽장문의_이동 {
	static int N;
	static int M;
	static int[] door;
	static int[][][] memo;

	private static int move(int index, int open1, int open2) {
		if(index == M) return 0;

		if(memo[index][open1][open2] != -1) return memo[index][open1][open2];

		int close = door[index];
		int cnt1 = move(index + 1, close, open2) + Math.abs(open1 - close);
		int cnt2 = move(index + 1, open1, close) + Math.abs(open2 - close);

		return memo[index][open1][open2] = Math.min(cnt1, cnt2);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int open1 = Integer.parseInt(st.nextToken());
		int open2 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		door = new int[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			door[i] = Integer.parseInt(st.nextToken());
		}
		memo = new int[M][N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N + 1; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		System.out.println(move(0, open1, open2));
	}
}
