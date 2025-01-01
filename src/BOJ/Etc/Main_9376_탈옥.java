package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * . : 빈공간
 * * : 지나갈 수 없는 벽
 * # : 문
 * $ : 죄수 위치
 */
public class Main_9376_탈옥 {
	static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static int H;
	static int W;
	static char[][] map;
	static final int[][] DIR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	private static boolean inRange(int y, int x) {
		return y >= 0 && y < H && x >= 0 && x < W;
	}

	private static int[][] bfs(Point start) {
		Deque<Point> deque = new LinkedList<>();
		int[][] dist = new int[H][W];
		for (int i = 0; i < H; i++) {
			Arrays.fill(dist[i], -1);
		}

		deque.addFirst(start);
		dist[start.y][start.x] = 0;

		while (!deque.isEmpty()) {
			Point here = deque.pollFirst();
			int y = here.y;
			int x = here.x;

			for (int i = 0; i < 4; i++) {
				int ny = y + DIR[i][0];
				int nx = x + DIR[i][1];
				if(!inRange(ny, nx)) continue;
				if(map[ny][nx] == '*' || dist[ny][nx] != -1) continue;

				if(map[ny][nx] == '#') {
					dist[ny][nx] = dist[y][x] + 1;
					deque.addLast(new Point(ny, nx));
				}
				else {
					dist[ny][nx] = dist[y][x];
					deque.addFirst(new Point(ny, nx));
				}
			}
		}

		return dist;
	}

	private static int solve() {
		Point person1 = null;
		Point person2 = null;

		int[][] dist0 = bfs(new Point(0, 0));

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] == '$') {
					if(person1 == null) person1 = new Point(i, j);
					else person2 = new Point(i, j);
				}
			}
		}

		int[][] dist1 = bfs(person1);
		int[][] dist2 = bfs(person2);

		int res = Integer.MAX_VALUE;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] == '*') continue;
				if(dist0[i][j] == -1 || dist1[i][j] == -1 || dist2[i][j] == -1) continue;

				int sum = dist0[i][j] + dist1[i][j] + dist2[i][j];
				if(map[i][j] == '#') sum -= 2;
				if(res > sum) res = sum;
			}
		}

		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken()) + 2;
			W = Integer.parseInt(st.nextToken()) + 2;

			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				map[i][0] = '.';
				map[i][W - 1] = '.';
			}
			for (int j = 0; j < W; j++) {
				map[0][j] = '.';
				map[H - 1][j] = '.';
			}

			for (int i = 0; i < H - 2; i++) {
				char[] ch = br.readLine().toCharArray();
				for (int j = 0; j < W - 2; j++) {
					map[i + 1][j + 1] = ch[j];
				}
			}

			sb.append(solve()).append("\n");
		}
		System.out.println(sb);
	}
}
