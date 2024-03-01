package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2839_설탕배달 {
	static int N;
	
	private static int solve() {
		int i = N / 5, j = 0;
		
		while(i >= 0) {
			if(i * 5 + j * 3 == N) return i + j;
			else if(i * 5 + j * 3 < N) j += 1;
			else {
				i -= 1;
				j = 0;
			}
		}
		
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		System.out.println(solve());
	}
}
