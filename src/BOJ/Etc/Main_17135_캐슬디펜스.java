package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17135_캐슬디펜스 {
	static int N;
	static int M;
	static int D;
	static int res;
	static int[][] map;
	static int[][] tmp;
	static int[] pos; // 궁수의 열 위치
	
	private static void init() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				tmp[i][j] = map[i][j];
			}
		}
	}
	
	private static boolean inRnage(int y, int x) {
		return 0 <= y && y < N & 0 <= x && x < M;
	}
	
	private static int[] find(int y, int x) {
		for(int d = 1; d <= D; d++) {
			for(int dx = -1 * d + 1; dx < d; dx++) {
				int nx = x + dx;
				int ny = y + (d - Math.abs(dx)) * -1;
				if(!inRnage(ny, nx)) continue;
				if(tmp[ny][nx] >= 1) return new int[] {ny, nx};
			}
		}
		return null;
	}
	
	private static int count() {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(tmp[i][j] <= 1) continue;
				tmp[i][j] = 0;
				cnt += 1;      
			}
		}
		return cnt;
	}
	
	private static int play() {
		init();
		
		int res = 0;
		for(int y = N; y > 0; y--) {
			for(int i = 0; i < 3; i++) {
				int[] next = find(y, pos[i]);
				if(next != null) tmp[next[0]][next[1]] += 1;
			}
			res += count();
		}
		return res;
	}
	
	private static void solve(int start, int index, int flag) {
		if(index == 3) {
			res = Math.max(res, play());
			return;
		}
		
		for(int i = start; i < M; i++) {
			if((flag & 1 << i) != 0) continue;
			pos[index] = i;
			solve(i + 1, index + 1, flag | (1 << i));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		tmp = new int[N][M];
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		res = 0;
		pos = new int[3];
		solve(0, 0, 0);
		System.out.println(res);
	}
}
