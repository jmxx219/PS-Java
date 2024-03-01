package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3197_백조의호수 {
	static class Point {
		int y, x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int R;
	static int C;
	static char[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static Point first;
	static Point second;
	static int[] parent;
	
	private static int find(int x) {
		if(x == parent[x]) return x;
		parent[x] = find(parent[x]);
		return parent[x];
	}
	
	
	private static void union(int u, int v) {
		int uP = find(u);
		int vP = find(v);
		if(uP < vP) parent[vP] = uP;
		else parent[uP] = vP;
	}
	

	private static boolean inRange(int y, int x) {
		return 0 <= y && y < R && 0 <= x && x < C;
	}
	
	private static void makeGroup(Queue<Point> queue) {
		boolean[][] visit = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == 'X') continue;
				
				for(int dir = 0; dir < 4; dir++) {
					int ny = i + dy[dir];
					int nx = j + dx[dir];
					if(!inRange(ny, nx)) continue;
					if(map[ny][nx] == '.') {
						union(i * C + j, ny * C + nx);
					}
					else if(map[ny][nx] == 'X' && !visit[ny][nx]) {
						visit[ny][nx] = true;
						queue.add(new Point(ny, nx));
					}
				}
			}
		}
	}
	
	private static Queue<Point> melting(Queue<Point> queue) {
		Queue<Point> nextQueue = new LinkedList<Point>();
		boolean[][] visit = new boolean[R][C];
		
		while(!queue.isEmpty()) {
			Point here = queue.poll();
			
			map[here.y][here.x] = '.';
			
			for(int dir = 0; dir < 4; dir++) {
				int ny = here.y + dy[dir];
				int nx = here.x + dx[dir];
				if(!inRange(ny, nx)) continue;
				
				if(map[ny][nx] == '.') {
					union(here.y * C + here.x, ny * C + nx);
				}
				else if(map[ny][nx] == 'X' && !visit[ny][nx]) {
					visit[ny][nx] = true;
					nextQueue.add(new Point(ny, nx));
				}
			}
		}
		return nextQueue;
	}

	
	private static int solve() {
		Queue<Point> queue = new LinkedList<Point>();
		
		makeGroup(queue);
		
		int time = 0;
		while(find(first.y * C + first.x) != find(second.y * C + second.x)) {
			queue = melting(queue);
			time += 1;
		}
		return time;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		parent = new int[R * C];
		
		boolean isFirst = false;
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < C; j++) {
				parent[i * C + j] = i * C + j;
				if(map[i][j] == 'L') { 
					if(!isFirst) first = new Point(i, j);
					else second = new Point(i, j);
					map[i][j] = '.';
					isFirst = true;
				}
			}
		}
		System.out.println(solve());
	}
}
