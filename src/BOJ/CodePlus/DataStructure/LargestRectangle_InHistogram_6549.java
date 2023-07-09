package BOJ.CodePlus.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class LargestRectangle_InHistogram_6549 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static long[] H;

    private static long solve() {
        Stack<Integer> stack = new Stack<>();
        long ans = 0;

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && H[stack.peek()] > H[i]) {
                long height = H[stack.pop()];
                long width = (stack.isEmpty()) ? i : (i - stack.peek() - 1);

                if(ans < (long) height * width) {
                    ans = (long) height * width;
                }
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            long height = H[stack.pop()];
            long width = (stack.isEmpty()) ? N : (N - stack.peek() - 1);

            if (ans < (long)width * height) {
                ans = (long)width * height;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N == 0) break;

            H = new long[N];
            for (int i = 0; i < N; i++) {
                H[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println(solve());
        }
    }
}
