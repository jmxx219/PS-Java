package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11559_PuyoPuyo {
	static final int N = 12;
	static final int M = 6;
	static int[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	public static int getNum(char ch) {
		if(ch == '.') return 0;
		else if(ch == 'R') return 1;
		else if(ch == 'G') return 2;
		else if(ch == 'B') return 3;
		else if(ch == 'P') return 4;
		return 5;
	}
	
	private static int puyoCount(int y, int x, boolean[][] visit) {
		int cnt = 1;
		visit[y][x] = true;
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
			if(map[ny][nx] == map[y][x] && !visit[ny][nx]) cnt += puyoCount(ny, nx, visit); 
		}
		return cnt;
	}
	
	private static void puyo(int y, int x, int color) {
		map[y][x] = 0;
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
			if(map[ny][nx] == color) puyo(ny, nx, color); 
		}
	}
	
	private static void move() {
		for(int j = 0; j < M; j++) {
			int num = N - 2, zero = N - 1;
			while(true) {
				while(zero >= 0 && map[zero][j] != 0) zero -= 1;
				num = zero - 1;
				while(num >= 0 && map[num][j] == 0) num -= 1;
				if(num == -1 || zero == -1) break;
				
				map[zero][j] = map[num][j];
				map[num][j] = 0;
				zero -= 1;
			}
		}
	}
	
	private static int solve() {
		int cnt = 0;
	
		while(true) {
			boolean flag = false;
			
			boolean[][] visit = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 0 || visit[i][j]) continue;
					if(puyoCount(i, j, visit) >= 4) {
						flag = true;
						puyo(i, j, map[i][j]);
					}
				}
			}
			
			if(flag) cnt += 1;
			else break;
			
			move();
		}
		
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				map[i][j] = getNum(ch[j]);
			}
		}
		System.out.println(solve());
	}
}
