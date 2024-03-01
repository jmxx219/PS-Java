package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_햄버거_다이어트 {
	static int N; // 재료의 수
	static int L; // 제한 칼로리
	static int[][] dish;
	
	private static int solve() {
		int maxScore = 0;
		for(int i = 1; i < (1<<N); i++) {
			int sc = 0;
			int cal = 0;
			for(int j = 0; j < N; j++) {
				if((i & (1 << j)) == 0) continue;
				sc += dish[j][0];
				cal += dish[j][1];
				if(cal >= L) break;
			}
			if(cal <= L && maxScore < sc) maxScore = sc;
		}
		return maxScore;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			dish = new int[N][2];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				dish[i][0] = Integer.parseInt(st.nextToken()); // 점수
				dish[i][1] = Integer.parseInt(st.nextToken()); // 칼로리
			}
			
			sb.append("#").append(t).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);

	}
}
