package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1247_최적경로 {
	static int N;
	static int[][] point;
	static int res;
	
	private static int calc(int i, int j) {
		return Math.abs(point[i][0] - point[j][0]) + Math.abs(point[i][1] - point[j][1]);
	}
	
	private static void solve(int index, int prev, int currDist, int flag) {
		if(index == N) {
			int dist = currDist + calc(prev, 1);
			if(res > dist) res = dist;
			return;
		}
		
		for(int i = 2; i < N + 2; i++) {
			if((flag & 1 << i) != 0) continue;
			solve(index + 1, i, currDist + calc(prev, i), flag | 1<<i);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			point = new int[N + 2][2];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N + 2; i++) {
				point[i][0] = Integer.parseInt(st.nextToken());
				point[i][1] = Integer.parseInt(st.nextToken());
			}
			res = Integer.MAX_VALUE;
			solve(0, 0, 0, 0);
			sb.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.println(sb);
	}
}
