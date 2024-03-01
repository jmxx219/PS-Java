package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1954_달팽이숫자 {
	private static final int[] dy = {0, 1, 0, -1};
	private static final  int[] dx = {1, 0, -1, 0};
	private static int N;
	private static int[][] map;
	
	private static boolean isRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
	
	private static void solve() {
		int y = 0, x = 0;
		int n = 1;
		int dir = 0;
		while(n <= N * N) {
			map[y][x] = n++;
			
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if(!isRange(ny, nx) || (isRange(ny, nx) && map[ny][nx] != 0)) dir = (dir + 1) % 4;
			y += dy[dir];
			x += dx[dir];
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			solve();
			
			sb.append("#").append(t).append("\n");
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
		
	}

}
