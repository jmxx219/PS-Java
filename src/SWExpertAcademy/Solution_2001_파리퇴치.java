package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2001_파리퇴치 {
	private static BufferedReader br;
	private static StringTokenizer st;
	private static int N;
	private static int M;
	private static int[][] map;
	
	private static int solve() {
		int res = 0;
		
		for(int i = 0; i <= N - M; i++) {
			for(int j = 0; j <= N - M; j++) {
				int sum = 0;
				for(int y = i; y < i + M; y++) {
					for(int x = j; x < j + M; x++) {
						sum += map[y][x];
					}
				}
				if(res < sum) res = sum;
			}
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
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.println("#" + t + " " + solve());
		}
	}

}
