package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2295_세수의합 {
	static int N;
	static int[] A;

	private static int solve() {
		int[] sums = new int[N * N];
		int index = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				sums[index++] = A[x] + A[y];
			}
		}

		Arrays.sort(A);
		Arrays.sort(sums);

		for (int k = N - 1; k >= 0; k--) {
			for (int z = N - 1; z >= 0; z--) {
				int target = A[k] - A[z];
				if(Arrays.binarySearch(sums, target) >= 0) return A[k];
			}
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = new int[N];
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(solve());
	}
}
