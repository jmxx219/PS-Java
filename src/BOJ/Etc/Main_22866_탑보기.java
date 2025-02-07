package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_22866_탑보기 {
	static int N;
	static int[] height;

	private static String solve() {
		int[] cnt = new int[N + 1];
		int[] near = new int[N + 1];

		Stack<Integer> stack = new Stack<>();
		for (int i = 1; i <= N; i++) {
			while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
				stack.pop();
			}
			cnt[i] = stack.size();
			if(stack.size() > 0) near[i] = stack.peek();
			stack.push(i);
		}

		stack = new Stack<>();
		for (int i = N; i >= 1; i--) {
			while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
				stack.pop();
			}
			cnt[i] += stack.size();
			if(stack.size() > 0 && (near[i] == 0 || (stack.peek() - i < i - near[i]))) near[i] = stack.peek();
			stack.push(i);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(cnt[i]);
			if(cnt[i] > 0) sb.append(" ").append(near[i]);
			sb.append("\n");
		}

		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		height = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solve());
	}
}
