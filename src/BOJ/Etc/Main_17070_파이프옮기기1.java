package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1 {
	static int N;
	static int[][] map;
	static int res;
	static final int[][] pipe = {{0, 2}, {1, 2}, {0, 1, 2}}; // 가로, 세로, 대각선(0 ~ 2)
	static final int[][] dir = {{0, 1}, {1, 0}, {1, 1}}; 
	
	private static boolean possible(int y, int x, int curr, int next) {
		for(int i = 0; i < 3; i++) {
			if(next != 2 && next != i) continue;
			int ny = y + dir[i][0];
			int nx = x + dir[i][1];
			if(ny < 0 || ny >= N || nx < 0 || nx >= N) return false;
			if(map[ny][nx] == 1) return false;
		}
		
		return true;
	}
	
	private static void solve(int y, int x, int curr) {
		if(y == N - 1 && x == N - 1) {
			res += 1;
			return;
		}
		for(int next : pipe[curr]) {
			if(!possible(y, x, curr, next)) continue;
			solve(y + dir[next][0], x + dir[next][1], next);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i = 0; i < N; i++ ) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve(0, 1, 0);
		System.out.println(res);
	}
}
