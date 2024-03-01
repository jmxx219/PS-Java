package BOJ.Etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제 설명]
 * - 첫번째 열에서부터 파이프를 설치해서 마지막 열까지 도달하면 하나의 파이프 라인 생성
 * - 설치할 수 있는 파이프라인의 최대 개수 구하기
 * 
 * [문제 풀이]
 * 그리디 + DFS
 * - 가장 많은 파이프라인을 구하기 위해서는 파이프라인을 항상 위쪽 방향으로 먼저 생성하거나, 아래쪽 방향으로 먼저 생성해야 한다.
 * 		1) 오른쪽 대각선 위, 오른쪽, 오른쪽 대각선 아래 방향 →→ dy = {-1, 0, 1}, r = 0 → R
 * 		2) 오른쪽 대각선 아래, 오른쪽, 오른쪽 대각선 위 방향 →→ dy = {1, 0, -1}, r = R-1 → 0
 */
public class Main_3109_빵집 {
	static int R;
	static int C;
	static char[][] map;
	static final int[] dy = {-1, 0, 1}; // 위, 중간, 아래
	static final int DIR = 3;
	
	private static boolean dfs(int y, int x) {
		if(x == C - 1) return true;
		
		for(int i = 0; i < DIR; i++) {
			int ny = y + dy[i];
			if(ny < 0 || ny >= R) continue;
			
			if(map[ny][x + 1] == '.') {
				map[ny][x + 1]= 'x';
				if(dfs(ny, x + 1)) return true;
			}
		}
		
		return false;
	}
	
	private static int solve() {
		int cnt = 0;
		
		for(int r = 0; r < R; r++) {
			if(dfs(r, 0)) cnt += 1;
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		System.out.println(solve());
	}

}
