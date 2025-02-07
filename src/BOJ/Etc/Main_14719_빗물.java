package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14719_빗물 {
	static int H;
	static int W;
	static int[] height;

	private static int solve() {
		int water = 0;

		for (int i = 0; i < W - 1; i++) {
			int left = 0;
			int right = 0;

			for (int j = 0; j < i; j++) {
				left = Math.max(left, height[j]);
			}
			for (int j = i + 1; j < W; j++) {
				right = Math.max(right, height[j]);
			}

			if(height[i] < left && height[i] < right) {
				water += Math.min(left, right) - height[i];
			}
		}

		return water;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		height = new int[W];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solve());
	}
}
