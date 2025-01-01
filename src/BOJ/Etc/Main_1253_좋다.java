package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1253_좋다 {
	static int N;
	static int[] A;
	static boolean[] check;

	private static int lowerBound(int target) {
		int lo = 0; int hi = A.length - 1;
		int ans = A.length;
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if(target <= A[mid]) {
				ans = mid;
				hi = mid - 1;
			}
			else {
				lo = mid + 1;
			}
		}
		return ans;
	}

	private static int upperBound(int target) {
		int lo = 0; int hi = A.length - 1;
		int ans = A.length;
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if(target < A[mid]) {
				ans = mid;
				hi = mid - 1;
			}
			else {
				lo = mid + 1;
			}
		}
		return ans;
	}

	private static void findTargetRange(int target, int i, int j) {
		int lb = lowerBound(target);
		int ub = upperBound(target);
		for(int n = lb; n < ub; n++){
			if(n != i && n != j) check[n] = true;
		}
	}

	private static int solve() {
		Arrays.sort(A);
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				int k = A[i] + A[j];
				if(k <= A[N - 1]) {
					findTargetRange(k, i, j);
				}
			}
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if(check[i]) cnt++;
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = new int[N];
		check = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solve());
	}
}
