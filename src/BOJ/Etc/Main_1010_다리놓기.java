package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_다리놓기 {
	static int N; // 서쪽
	static int M; // 동쪽
	
	private static double fact(int n) {
		double res = 1.0;
		for(int i = 1; i <= n; i++) {
			res *= i;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			double res = fact(M) / fact(N) / fact(M - N);
			System.out.println(Math.round(res));
		}
	}
}
