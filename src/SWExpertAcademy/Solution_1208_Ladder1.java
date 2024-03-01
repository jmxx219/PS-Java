package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1208_Ladder1 {
	private static BufferedReader br;
	private static StringTokenizer st;
	private static final int N = 100;
	private static final int T = 10;
	private static int[][] map;
	private static final int[] dy  = {-1, 0, 0}; // 위, 좌, 우
	private static final int[] dx = {0, -1, 1};
	
	private static boolean isRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	} 
	
	private static int isLeftRight(int y, int x) {
		for(int i = 1; i < 3; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(isRange(ny, nx) && map[ny][nx] == 1) return i;
		}
		return 0;
	}
	
	private static int solve(int y, int x) {
		int dir = 0;
		
		while(y != 0) {
			int ndir = isLeftRight(y, x);
			if(ndir > 0) {
				int ny = y + dy[ndir];
				int nx = x + dx[ndir];
				while(isRange(ny, nx) && map[ny][nx] == 1) {
					y = ny;
					x = nx;
					ny += dy[ndir];
					nx += dx[ndir];
				}
			}
			
			y += dy[dir];
			x += dx[dir];
		}
		
		return x;
	}

	// 아래 방향이 기본, 좌우 방향 나타나면 방향 전환
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[N][N];
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			
			int endY = -1, endX = -1;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 2) {
						endY = i;
						endX = j;
					}
				}
			}
			System.out.println("#" + t + " " + solve(endY, endX));
		}
	}

}
