package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_18869_멀티버스2 {
	static int M; // 우주의 개수
	static int N; // 행성의 개수
	static int[][] spaces;

	private static int binarySearch(int[] sorted, int planet) {
		int lo = 0, hi = sorted.length - 1;
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if(planet <= sorted[mid]) {
				hi = mid;
			}
			else if(planet > sorted[mid]) {
				lo = mid + 1;
			}
		}
		return lo;
	}

	private static void compress(int[] space) {
		int[] sorted = Arrays.stream(space).distinct().sorted().toArray();
		for (int i = 0; i < N; i++) {
			space[i] = binarySearch(sorted, space[i]);
		}
	}

	private static int countPair() {
		for (int i = 0; i < M; i++) {
			compress(spaces[i]);
		}
		int cnt = 0;
		for (int i = 0; i < M - 1; i++) {
			for (int j = i + 1; j < M; j++) {
				if(Arrays.equals(spaces[i], spaces[j])) {
					cnt += 1;
				}
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		spaces = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				spaces[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(countPair());
	}
}
