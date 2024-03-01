package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2806_NQueen {

	static int N;
	
	private static boolean ok(int row, int[] cols) {
		for(int r = 0; r < row; r++) {
			if(cols[r] == cols[row] || Math.abs(row - r) == Math.abs(cols[r] - cols[row])) {
				return false;
			}
		}
		return true;
	}
	
	private static int solve(int row, int[] cols) {
		if(!ok(row - 1, cols)) return 0;
		if(row == N) return 1;
		
		int cnt = 0;
		for(int c = 0; c < N; c++) {
			cols[row] = c;
			cnt += solve(row + 1, cols);
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			sb.append("#").append(t).append(" ").append(solve(0, new int[N])).append("\n");
		}
		System.out.println(sb);
	}

}
