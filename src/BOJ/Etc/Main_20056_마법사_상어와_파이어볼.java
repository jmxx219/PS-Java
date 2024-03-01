package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_20056_마법사_상어와_파이어볼 {
	
	static class Node {
		int y, x;
		int m; // 질량
		int s; // 속도
		int d; // 방향
		public Node(int y, int x, int m, int s, int d) {
			super();
			this.y = y;
			this.x = x;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	static int N; // 격자 크기
	static int M; // 파이어볼 개수
	static int K; // 명령 횟수
	static int[][] map;
	static List<Node> list;
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

	private static void move(int index) {
		Node node = list.get(index);
		int ny = (node.y + dy[node.d] * node.s) % N;
		int nx = (node.x + dx[node.d] * node.s) % N;
		if(ny < 0) ny = N - (-1 * ny) % N;
		if(nx < 0) nx = N - (-1 * nx) % N;
		list.get(index).y = ny;
		list.get(index).x = nx;
		map[ny][nx] += 1;
	}
	
	private static int solve() {
		while(K-- > 0) {
			for(int i = 0; i < list.size(); i++) move(i);
			
			for(int i = 0; i < list.size(); i++) {
				Node node = list.get(i);
				
				if(map[node.y][node.x] >= 2) {
					int m = node.m;
					int s = node.s;
					boolean same = true;
					for(int j =  i + 1; j < list.size(); j++) {
						if(node.y == list.get(j).y && node.x == list.get(j).x) {
							m += list.get(j).m;
							s += list.get(j).s;
							if(node.d % 2 != list.get(j).d % 2) same = false;
							list.remove(j);
							j -= 1;
						}
					}
					list.remove(i);
					i -= 1;
					
					if(m / 5 != 0) {
						int k = (same) ? 0 : 1;
						m /= 5;
						s /= map[node.y][node.x];
						for(; k < 8; k += 2) { 
							list.add(new Node(node.y, node.x, m, s, k));
						}
					}
				}
				map[node.y][node.x] = 0;
				
			}
		}
		
		return list.stream().mapToInt(s -> s.m).sum();
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		list = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new Node(y, x, m, s, d));
		}
		
		System.out.println(solve());
	}

}
