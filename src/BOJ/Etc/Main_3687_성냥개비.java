package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3687_성냥개비 {
	static final int MAX = 100;

	private static long[] findMinNumber() {
		long[] minDp = new long[MAX + 1];
		Arrays.fill(minDp, Long.MAX_VALUE);

		int[] add = {1, 7, 4, 2, 0, 8};
		minDp[2] = add[0];
		minDp[3] = add[1];
		minDp[4] = add[2];
		minDp[5] = add[3];
		minDp[6] = 6;
		minDp[7] = add[5];
		minDp[8] = 10;

		for (int i = 9; i <= MAX; i++) {
			for (int j = 2; j <= 7; j++) {
				long num = minDp[i - j] * 10 + add[j - 2];
				minDp[i] = Math.min(minDp[i], num);
			}
		}
		return minDp;
	}

	private static String[] findMaxNumber() {
		String[] maxNum = new String[MAX + 1];
		for (int i = 2; i <= MAX; i++) {
			StringBuilder sb = new StringBuilder();

			if(i % 2 == 0) sb.append(1);
			else sb.append(7);

			for (int j = 0; j < i / 2 - 1; j++) {
				sb.append(1);
			}

			maxNum[i] = sb.toString();
		}
		return maxNum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		long[] minDp = findMinNumber();
		String[] maxNum = findMaxNumber();

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			sb.append(minDp[N]).append(" ").append(maxNum[N]).append("\n");
		}
		System.out.println(sb);
	}
}
