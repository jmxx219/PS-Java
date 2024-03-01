package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2805_농작물수확하기 {
	private static BufferedReader br;
	private static StringTokenizer st;
	private static int N;
	private static int[][] map;
	
	private static int solve() {
		int res = 0;
		
		int blank = N / 2;
		for(int i = 0; i < N; i++) {
			for(int j = blank; j <= N - blank - 1; j++) {
				res += map[i][j];
			}
			
			if(i < N / 2) blank -= 1;
			else blank += 1;
		}
		
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			}

			System.out.println("#" + t + " " + solve());
		}
	}

}
