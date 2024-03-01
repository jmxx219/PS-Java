package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17281_야구 {
	// 아웃, 안타, 2루타, 3루타, 홈런(0 ~ 4) 
	static int N; // 이닝 수
	static final int PLAYER = 9;
	static int[][] scores;
	static int maxScore;
	static int[] order;
	
	private static int move(int curr, boolean[] pos) {
		int score = 0;
		for(int j = 3; j >= 1; j--) {
			if(pos[j] && j + curr > 3) {
				score += 1;
				pos[j] = false;
			}
			else if(pos[j]){
				pos[j + curr] = true;
				pos[j] = false;
			}
		}
		if(curr == 4) score += 1;
		else pos[curr] = true;
		return score;
	}
	
	private static int play() {
		int turn = 0;
		int out = 0;
		int score = 0;
		int index = 0;
		
		boolean[] pos = new boolean[4];
		
		while(turn < N) {
			while(out < 3) {
				int curr = scores[turn][order[index]];
				
				if(curr == 0) out += 1;
				else score += move(curr, pos);
				
				index = (index + 1) % PLAYER;
			}
			out = 0;
			turn += 1;
			Arrays.fill(pos, false);
		}
		
		return score;
	}
	
	private static void solve(int index, int flag) {
		if(index == PLAYER) {
			maxScore = Math.max(maxScore, play());
			return;
		}
		
		if(index == 3) {
			order[index] = 0;
			solve(index + 1, flag | (1 << 0));
			return;
		}
		
		for(int i = 1; i < PLAYER; i++) {
			if((flag & 1 << i) != 0) continue;
			order[index] = i;
			solve(index + 1, flag | (1 << i));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		scores = new int[N][PLAYER];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < PLAYER; j++) {
				scores[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		maxScore = 0;
		order = new int[PLAYER];
		solve(0, 0);
		System.out.println(maxScore);
	}
}
