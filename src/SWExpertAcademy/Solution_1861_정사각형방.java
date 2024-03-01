package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1861_정사각형방 {

	static int N;
	static int[][] map;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	private static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
	
	private static int move(int y, int x) {
		int cnt = 1;

		while(true) {
			int ny, nx;
			boolean next = false;
			for(int i = 0; i < 4; i++) {
				ny = y + dy[i];
				nx = x + dx[i];
				if(!inRange(ny, nx)) continue;
				if(map[y][x] + 1 == map[ny][nx]) {
					cnt += 1;
					y = ny;
					x = nx;
					next = true;
					break;
				}
			}
			if(!next) break;
		}
		return cnt;
	}
	
	private static int[] solve() {
		int[] res = {N * N + 1, 0};
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int cnt = move(i, j);
				if((res[1] < cnt) || (res[1] == cnt && res[0] > map[i][j])) {
					res[0] = map[i][j];
					res[1] = cnt;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] res = solve();
			sb.append('#').append(t).append(' ').append(res[0]).append(' ').append(res[1]).append("\n");
		}
		System.out.println(sb);
		
	}

}
