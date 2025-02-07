package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1027_고층건물 {
	static int N;
	static int[] height;

	private static double calcInc(int idx, int i) {
		return (double) (height[idx] - height[i]) / (idx - i);
	}

	private static int counting(int idx) {
		int cnt = 0;

		double inc = 0.0;
		for (int i = idx - 1; i >= 0; i--) {
			double tmpInc = calcInc(idx, i);
			if(i == idx - 1 || inc > tmpInc) {
				cnt++;
				inc = tmpInc;
			}
		}

		for (int i = idx + 1; i < N; i++) {
			double tmpInc = calcInc(idx, i);
			if(i == idx + 1 || inc < tmpInc) {
				cnt++;
				inc = tmpInc;
			}
		}

		return cnt;
	}

	private static int solve() {
		int res = 0;
		for (int i = 0; i < N; i++) {
			res = Math.max(res, counting(i));
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		height = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solve());
	}
}
