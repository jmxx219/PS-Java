package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_8983_사냥꾼 {
	static int M; // 사대 수
	static int N; // 동물 수
	static int L; // 사정거리
	static int[] S;
	static int[][] animals;

	private static boolean inRange(int x, int a, int b) {
		return Math.abs(x - a) + b <= L;
	}

	private static boolean canCatch(int a, int b) {
		int lo = 0;
		int hi = S.length - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int x = S[mid];

			if(inRange(x, a, b)) return true;

			if(a > x) lo = mid + 1;
			else hi = mid - 1;
		}

		return false;
	}

	private static int solve() {
		Arrays.sort(S);

		int res = 0;
		for (int i = 0; i < N; i++) {
			if(canCatch(animals[i][0], animals[i][1])) {
				res++;
			}
		}

		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		S = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}

		animals = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			animals[i][0] = Integer.parseInt(st.nextToken());
			animals[i][1] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solve());
	}
}
