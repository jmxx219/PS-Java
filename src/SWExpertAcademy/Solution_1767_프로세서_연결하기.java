package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Solution_1767_프로세서_연결하기 {
	static int N;
	static int[][] map;
	static List<int[]> cores;
	static int coreCnt;
	static int lineSum;
	static int[][] dir = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
	
	private static boolean isEnd(int y, int x) {
		return y == 0 || x == 0 || y == N - 1 || x == N - 1;
	}
	
	private static int makeLine(int index, int d) {
		int lineCnt = 0;
		int y = cores.get(index)[0];
		int x = cores.get(index)[1];
		
		boolean ok = true;
		while(!isEnd(y, x)) {
			y += dir[d][0];
			x += dir[d][1];
			if(map[y][x] == 1) {
				ok = false;
				break;
			}
			lineCnt += 1;
			map[y][x] = 1;
		}
		if(!ok) {
			while(lineCnt-- > 0) {
				y += dir[3 - d][0];
				x += dir[3 - d][1];
				map[y][x] = 0;
			}
		}
		return ok ? lineCnt : -1;
	}
	
	private static void unMakeLine(int index, int d, int cnt) {
		int y = cores.get(index)[0] + dir[d][0];
		int x = cores.get(index)[1] + dir[d][1];
		while(cnt-- > 0) {
			map[y][x] = 0;
			y += dir[d][0];
			x += dir[d][1];
		}
	}
	
	private static void solve(int index, int cnt, int sum) {
		if(cnt > coreCnt) {
			coreCnt = cnt;
			lineSum = sum;
		}
		else if(cnt == coreCnt && lineSum > sum) lineSum = sum;
		
		if(index == cores.size()) return;
		
		for(int i = 0; i < 4; i++) {
			int tmpSum = makeLine(index, i);
			if(tmpSum != -1) {
				solve(index + 1, cnt + 1, sum + tmpSum);
				unMakeLine(index, i, tmpSum);
			}
		}
		solve(index + 1, cnt, sum);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			cores = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1 && !isEnd(i, j)) cores.add(new int[] {i, j});
				}
			}
			coreCnt = 0;
			lineSum = 0;
			solve(0, 0, 0);
			sb.append("#").append(t).append(" ").append(lineSum).append("\n");
		}
		System.out.println(sb);

	}
}
