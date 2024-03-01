package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14502_연구소 {

	public static int N;
	public static int M;
	public static int[] dy = {-1, 0, 0, 1};
	public static int[] dx = {0, -1, 1, 0};
	static int[][] map;
	static int res;

	// 0:빈칸, 1:벽, 2:바이러스
	// 새로운 벽의 개수는 3개
	public static int[][] copy(int [][] arr) {
		int[][] copyWall = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyWall[i][j] = arr[i][j];
			}
		}
		return copyWall;
	}
	
	private static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	public static void check(int[][] map, int y, int x) {
		map[y][x] = 3;
		for(int i = 0; i < 4; i++) {
	         int ny = y + dy[i];
	         int nx = x + dx[i];
	         if(!inRange(ny, nx)) continue;
	         if(map[ny][nx] == 0 || map[ny][nx] == 2) check(map, ny, nx);
		}
	}
	
	public static int countSafeMap() {
		int[][] copyMap = copy(map);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 2) check(copyMap, i, j);
			}
		}

		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyMap[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}
	
	public static void solve(int wall) {
		if(wall == 3) {
			res = Math.max(res, countSafeMap());
			return;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					solve(wall + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		res = 0;
		solve(0);
		System.out.println(res);
	}

}
