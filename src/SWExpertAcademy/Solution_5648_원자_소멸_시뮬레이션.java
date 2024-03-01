package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5648_원자_소멸_시뮬레이션 {
	static class Node {
		int y, x;
		int d;
		int k;
		
		public Node(int y, int x, int d, int k) {
			this.y = y;
			this.x = x;
			this.d = d;
			this.k = k;
		}
	}
	
	static int N;
	static Queue<Node> queue;
	static int[][] map;
	static final int[] dy = {-1, 1, 0, 0}; // 상, 하, 좌, 우
	static final int[] dx = {0, 0,  -1, 1};
	static int INF = 4000;
	
	public static boolean inRange(int y, int x) {
		return 0 <= y && y <= INF && 0 <= x && x <= INF;
	}
	
	public static int solve() {
		int energySum = 0;
		
		while(!queue.isEmpty()) {
			for(int i = 0; i < queue.size(); i++) {
				Node node = queue.poll();
				
				if(map[node.y][node.x] != node.k) { // 충돌
					energySum += node.k;
					map[node.y][node.x] = 0;
					continue;
				}
				else map[node.y][node.x] -= node.k;
				
				int ny = node.y + dy[node.d];
				int nx = node.x + dx[node.d];
				if(inRange(ny, nx)) {
					queue.add(new Node(ny, nx, node.d, node.k));
					map[ny][nx] += node.k;
				}

			}
		}
		return energySum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		map = new int[INF + 1][INF + 1];
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			queue = new LinkedList<>();
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int y = INF - (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int d = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				map[y][x] = k;
				queue.offer(new Node(y, x, d, k));
			}
			
			sb.append("#").append(t).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);
	}

}
