package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범_검거 {
	static int N;
	static int M;
	static int L;
	static final int[] dy = {-1, 1, 0, 0}; // 상, 하, 좌, 우
	static final int[] dx = {0, 0, -1, 1};
	static int[][] map;
	
	private static boolean possible(int dir, int type) {
		if(type == 1) return true;
		else if(type == 2 && (dir == 0 || dir == 1)) return true;
		else if(type == 3 && (dir == 2 || dir == 3)) return true;
		else if(type == 4 && (dir == 0 || dir == 3)) return true;
		else if(type == 5 && (dir == 1 || dir == 3)) return true;
		else if(type == 6 && (dir == 1 || dir == 2)) return true;
		else if(type == 7 && (dir == 0 || dir == 2)) return true;
		return false;
	}
	
	private static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	private static boolean connect(int dir, int nextType) {
		int nextDir = 0;
		if(dir == 0) nextDir = 1;
		else if(dir == 1) nextDir = 0;
		else if(dir == 2) nextDir = 3;
		else if(dir == 3) nextDir = 2;
		return possible(nextDir, nextType);
	}
	
	private static int solve(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		int[][] time = new int[N][M];
		int cnt = 1;
		
		queue.add(new int[] {r, c});
		time[r][c] = 1;
		
		while(!queue.isEmpty()) {
			int y = queue.peek()[0];
			int x = queue.peek()[1];
			queue.poll();
			
			if(time[y][x] == L) continue;
			
			int type = map[y][x];
			
			for(int i = 0; i < 4; i++) {
				if(!possible(i, type)) continue;
				int ny = y + dy[i];
				int nx = x + dx[i];
				if(!inRange(ny, nx)) continue;
				
				if(!connect(i, map[ny][nx])) continue; // 두 개 연결
				if(time[ny][nx] == 0 && map[ny][nx] != 0) {
					time[ny][nx] = time[y][x] + 1;
					queue.add(new int[] {ny, nx});
					cnt += 1;
				}
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append("#").append(t).append(" ").append(solve(R, C)).append("\n");
		}
		System.out.println(sb);
	}
}
