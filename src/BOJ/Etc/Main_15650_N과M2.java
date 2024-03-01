package BOJ.Etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15650_N과M2 {
	private static BufferedReader br;
	private static StringTokenizer st;
	private static int N;
	private static int M;
	private static int[] picked;
	
	private static void solve(int index, int start, boolean[] check) {
		if(index == M) {
			for(int x : picked) System.out.print(x + " ");
			System.out.println();
			return;
		}
		
		for(int i = start; i <= N; i++) {
			if(!check[i]) {
				check[i] = true;
				picked[index] = i;
				solve(index + 1, i + 1, check);
				check[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		picked = new int[M];
		solve(0, 1, new boolean[N + 1]);
	}
}
