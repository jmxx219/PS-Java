package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2563_색종이 {
	static int N;
	static int[][] yx;
	static int minX, minY;
	static int maxX, maxY;
	static boolean[][] color;

	private static void draw(int y, int x) {
		for(int i = y; i < y + 10; i++) {
			for(int j = x; j < x + 10; j++) {
				color[i][j] = true;
			}
		}
	}
	
	private static int solve() {
		color = new boolean[maxY + 10][maxX + 10];
		for(int i = 0; i < N; i++) {
			draw(yx[i][0], yx[i][1]);
		}
		
		int cnt = 0;
		for(int i = minY; i < maxY + 10; i++) {
			for(int j = minX; j < maxX + 10; j++) {
				if(color[i][j]) cnt += 1;
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		yx = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			yx[i][0] = y;
			yx[i][1] = x;
			if(minX == 0 || minX > x) minX = x;
			if(minY == 0 || minY > y) minY = y;
			if(maxX == 0 || maxX < x) maxX = x;
			if(maxY == 0 || maxY < y) maxY = y;
 		}
		
		System.out.println(solve());
	}
}
