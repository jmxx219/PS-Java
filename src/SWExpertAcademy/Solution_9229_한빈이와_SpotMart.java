package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_9229_한빈이와_SpotMart {
	static int N; // 과자 봉지의 개수
	static int M; // 무게 합 제한
	static int[] snack;
	
	private static int solve() {
		int res = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == j) continue;
				int w = snack[i] + snack[j];
				if(w <= M && res < w) {
					res = w;
				}
			}
		}
		return res == 0 ? -1 : res;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st  = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			snack = new int[N];
			st  = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) snack[i] = Integer.parseInt(st.nextToken());
			
			solve();
			System.out.println("#" + t + " " + solve());
		}
	}

}
