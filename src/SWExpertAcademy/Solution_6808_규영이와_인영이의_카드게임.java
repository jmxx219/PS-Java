package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6808_규영이와_인영이의_카드게임 {
	static final int N = 9;
	static int[] A; // 규영
	static int[] B; // 인영
	static boolean[] check;
	static int win;
	static int lose;

	public static void solve(int index, int aScore, int bScore, int flag) {
		if(index == N) {
			if(aScore > bScore) win += 1;
			else if(aScore < bScore) lose += 1;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if((flag & (1 << i)) != 0) continue;
			
			int aTmp = aScore, bTmp = bScore;
			if(A[index] > B[i]) aTmp += (A[index] + B[i]);
			else if(A[index] < B[i]) bTmp += (A[index] + B[i]);
			
			solve(index + 1, aTmp, bTmp, flag | (1 << i));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			A = new int[N];
			B = new int[N];
			check = new boolean[N  * 2 + 1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				check[A[i]] = true;
			}
			
			int index = 0;
			for(int i = 1; i <= N * 2; i++) {
				if(!check[i]) B[index++] = i;
			}
			win = lose = 0;
			solve(0, 0, 0, 0);

			sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		System.out.println(sb);
		
	}

}
