package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 휴게소가 없는 구간의 길이의 최댓값을 최소
 */
public class Main_1477_휴게소세우기 {
	static int N; // 현재 휴게소 개수
	static int M; // 추가할 휴게소 개수
	static int L; //  고속도고 길이
	static int[] pos;

	private static boolean isPossible(int section, int m) {
		int curr = pos[0];
		for (int i = 1; i < N + 2; i++) {
			int next = pos[i];
			while (next - curr > section) {
				if(m <= 0) return false;
				curr += section;
				m--;
			}
			curr = next;
		}

		return curr == L ? true : false;
	}

	private static int solve() {
		Arrays.sort(pos);

		int res =  0;
		int lo = 1, hi = L;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if(isPossible(mid, M)) {
				res = mid;
				hi = mid - 1;
			}
			else lo = mid + 1;
		}

		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		pos = new int[N + 2];
		pos[N + 1] = L;
		if(N != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				pos[i] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solve());
	}
}
