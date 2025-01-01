package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5427_불 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int R;
	static int C;
	static char[][] map;
	static Point start;
	static List<Point> fireStart;
	static final int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static final int INF = 987654321;

	private static boolean isPossible(int r, int c) {
		return r == 0 || r == R - 1 || c == 0 || c == C - 1;
	}

	private static boolean inRange(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

	private static int[][] moveFire() {
		Queue<Point> queue = new LinkedList<>();
		int[][] dist = new int[R][C];
		for (int i = 0; i < R; i++) {
			Arrays.fill(dist[i], INF);
		}

		for(Point fire : fireStart) {
			queue.add(fire);
			dist[fire.r][fire.c] = 1;
		}

		while (!queue.isEmpty()) {
			Point fire = queue.poll();
			for(int[] dir : DIR) {
				int nr = fire.r + dir[0];
				int nc = fire.c + dir[1];
				if(!inRange(nr, nc)) continue;
				if(map[nr][nc] == '#') continue;
				if(dist[nr][nc] == INF) {
					dist[nr][nc] = dist[fire.r][fire.c] + 1;
					queue.add(new Point(nr, nc));
				}
			}
		}
		return dist;
	}

	private static int solve() {
		int[][] fireDist = moveFire();

		Queue<Point> queue = new LinkedList<>();
		int[][] dist = new int[R][C];

		queue.add(start);
		dist[start.r][start.c] = 1;

		while (!queue.isEmpty()) {
			Point here = queue.poll();
			if(isPossible(here.r, here.c)) return dist[here.r][here.c];
			for(int[] dir : DIR) {
				int nr = here.r + dir[0];
				int nc = here.c + dir[1];
				if(!inRange(nr, nc)) continue;
				if(map[nr][nc] == '#') continue;
				if(dist[nr][nc] == 0 && fireDist[nr][nc] > dist[here.r][here.c] + 1) {
					dist[nr][nc] = dist[here.r][here.c] + 1;
					queue.add(new Point(nr, nc));
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());

			fireStart = new ArrayList<>();

			map = new char[R][C];
			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < C; j++) {
					if(map[i][j] == '@') start = new Point(i, j);
					else if(map[i][j] == '*') fireStart.add(new Point(i, j));
				}
			}

			int res = solve();
			if(res == -1) sb.append("IMPOSSIBLE").append("\n");
			else sb.append(res).append("\n");
		}
		System.out.println(sb);
	}
}
