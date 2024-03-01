package BOJ.Etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2023_신기한소수 {
	private static BufferedReader br;
	private static StringTokenizer st;
	private static int N;
	
	private static boolean isPrime(int x) {
		if(x <= 1) return false;
		if(x == 2) return true;
		for(int i = 2; i * i <= x; i++) {
			if(x % i == 0) return false;
		}
		return true;
	}

	private static void solve(int index, int num, StringBuilder sb) {
		if(index == N) {
			sb.append(num).append("\n");
			return;
		}
		
		for(int i = 1; i < 10; i++) {
			int x = num * 10 + i;
			if(isPrime(x)) solve(index + 1, x, sb);
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		solve(0, 0, sb);
		System.out.println(sb);
	}

}
