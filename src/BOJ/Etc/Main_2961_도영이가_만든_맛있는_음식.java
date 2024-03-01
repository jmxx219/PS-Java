package BOJ.Etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_도영이가_만든_맛있는_음식 {
	private static BufferedReader br;
	private static StringTokenizer st;
	private static int N;
	private static int[] A;
	private static int[] B;
	private static final int INF = 1_000_000_000;
	
	private static int solve() {
		int res = INF;
		
		for(int i = 1; i < (1 << N); i++) {
			int aDish = 1;
			int bDish = 0;
			for(int j = 0; j < N; j++) {
				if((i & (1 << j)) != 0) {
					aDish *= A[j];
					bDish += B[j];
				}
			}
			res = Math.min(res, Math.abs(aDish - bDish));
		}
		
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		B = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solve());
	}

}
