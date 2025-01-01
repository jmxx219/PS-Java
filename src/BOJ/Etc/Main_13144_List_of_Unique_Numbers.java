package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13144_List_of_Unique_Numbers {
	static int N;
	static int[] A;
	static final int MAX = 100_000;

	private static long solve() {
		long res = 0;
		boolean[] check = new boolean[MAX + 1];

		int left = 0, right = 0;
		check[left] = true;
		while (right < N) {
			while(left < N && check[A[right]]) {
				check[A[left]] = false;
				left++;
			}
			check[A[right]] = true;
			res += (right - left + 1);
			right++;
		}

		return res;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solve());
	}
}
