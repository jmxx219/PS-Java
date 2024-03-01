package BOJ.Etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_6987_월드컵 {
	static final int N = 6;
	static int[][] score;
	static int[][] result;
	
	private static boolean solve(int a, int b) {
		if(a == N - 2 && b == N) {
			return true;
		}
		
		if(b == N) {
			a += 1;
			b = a + 1;
		}

		if(score[a][0] < result[a][0] && score[b][2] < result[b][2]) {
			score[a][0] += 1;
			score[b][2] += 1;
			if(solve(a, b + 1)) return true;
			score[a][0] -= 1;
			score[b][2] -= 1;
		}

		if(score[a][2] < result[a][2] && score[b][0] < result[b][0]) {
			score[a][2] += 1;
			score[b][0] += 1;
			if(solve(a, b + 1)) return true;
			score[a][2] -= 1;
			score[b][0] -= 1;
		}

		if(score[a][1] < result[a][1] && score[b][1] < result[b][1]) {
			score[a][1] += 1;
			score[b][1] += 1;
			if(solve(a, b + 1)) return true;
			score[a][1] -= 1;
			score[b][1] -= 1;
		}
		
		return false;

	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int i = 0; i < 4; i++) {
			result = new int[N][3];
			score = new int[N][3];
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				result[j][0] = Integer.parseInt(st.nextToken());
				result[j][1] = Integer.parseInt(st.nextToken());
				result[j][2] = Integer.parseInt(st.nextToken());
			}
			
			if(solve(0, 1)) System.out.print("1 ");
			else System.out.print("0 ");
		}
		System.out.println();
	}

}
