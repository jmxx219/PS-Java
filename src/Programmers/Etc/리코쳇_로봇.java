package Programmers.Etc;

import java.util.*;

public class 리코쳇_로봇 {
	static class Point {
		int y, x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static int N;
	static int M;
	static char[][] map;
	static Point R;
	static Point G;
	static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	private boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	private boolean canGo(int y, int x) {
		for(int i = 0; i < 4; i++) {
			int ny = y + DIR[i][0];
			int nx = x + DIR[i][1];
			if(!inRange(ny, nx)) return true;
			if(map[ny][nx] == 'D') return true;
		}
		return false;
	}

	private int findPath() {
		if(!canGo(G.y, G.x)) return -1;
		Queue<Point> queue = new LinkedList<>();
		int[][] dist = new int[N][M];
		for(int i = 0; i < N; i++) Arrays.fill(dist[i], -1);

		queue.add(R);
		dist[R.y][R.x] = 0;

		while(!queue.isEmpty()) {
			Point here = queue.poll();

			if(here.y == G.y && here.x == G.x) break;

			for(int i = 0; i < 4; i++) {
				int ny = here.y;
				int nx = here.x;
				while(true) {
					int nny = ny + DIR[i][0];
					int nnx = nx + DIR[i][1];
					if(!inRange(nny, nnx)) break;
					if(map[nny][nnx] == 'D') break;
					ny = nny;
					nx = nnx;
				}
				if(dist[ny][nx] == -1) {
					dist[ny][nx] = dist[here.y][here.x] + 1;
					queue.add(new Point(ny, nx));
				}
			}

		}

		return dist[G.y][G.x];
	}

	public int solution(String[] board) {
		N = board.length;
		M = board[0].length();
		map = new char[N][M];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = board[i].charAt(j);
				if(map[i][j] == 'R') R = new Point(i, j);
				if(map[i][j] == 'G') G = new Point(i, j);
			}
		}

		return findPath();
	}
}
