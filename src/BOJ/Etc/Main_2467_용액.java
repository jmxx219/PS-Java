package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2467_용액 {
	static int N;
	static int[] nums;

	private static String solve() {
		int lo = 0;
		int hi = N - 1;

		int diff = 2_000_000_000;
		int num1 = 0, num2 = 0;

		while (lo < hi) {
			int mid = nums[lo] + nums[hi];
			if(diff > Math.abs(mid)) {
				diff = Math.abs(mid);
				num1 = nums[lo];
				num2 = nums[hi];
			}
			if(mid < 0) lo++;
			else hi--;
		}

		return num1 + " " + num2;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solve());
	}
}
