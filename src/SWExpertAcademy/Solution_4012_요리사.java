package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_요리사 {
	static int N;
	static int[][] dish;

	public static int solve() {
		int res = Integer.MAX_VALUE;

		for (int k = 0; k < (1 << N); k++) {
			int[] team = new int[2]; // a: 0, b:1
			int size = 0;
			
			for (int i = 0; i < N; i++) {
				int t1 = (k & (1 << i)) == 0 ? 0 : 1;
				for(int j = i + 1; j < N; j++) {
					int t2 = (k & (1 << j)) == 0 ? 0 : 1;
					if(t1 == t2) team[t1] += dish[i][j] + dish[j][i];
				}
				
				if(t1 == 0) size += 1;
				
			}

			if (size == N / 2) {
				res = Math.min(res, Math.abs(team[0] - team[1]));
			}
		}

		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			dish = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					dish[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append("#").append(t).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);
	}

}
