package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InsertOperator_14888 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] nums;
    private static int[] operator;
    private static int ansMax = Integer.MIN_VALUE;
    private static int ansMin = Integer.MAX_VALUE;
    private static int[] picked;
    private static boolean[] visited;

    private static int calc() {
        int res = nums[0];

        for(int i = 0; i < N - 1; i++) {
            switch (picked[i]) {
                case 0: // 덧셈
                    res += nums[i + 1];
                    break;
                case 1: // 뺄셈
                    res -= nums[i + 1];
                    break;
                case 2: // 곱셈
                    res *= nums[i + 1];
                    break;
                case 3: // 나눗셈
                    if(res < 0) {
                        res = Math.floorDiv(res * -1, nums[i + 1]);
                        res *= -1;
                    }
                    else {
                        res = Math.floorDiv(res, nums[i + 1]);
                    }
                    break;
            }
        }

        return res;
    }

    private static void solve(int index) {
        if(index == N - 1) {
            int res = calc();
            ansMax = Math.max(ansMax, res);
            ansMin = Math.min(ansMin, res);
            return;
        }

        for (int i = 0; i < N - 1; i++) {
            if(!visited[i]) {
                visited[i] = true;
                picked[index] = operator[i];
                solve(index + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        operator = new int[N-1]; // 0: 덧셈, 1: 뺄셈, 2: 곱셈, 3: 나눗셈
        st = new StringTokenizer(br.readLine());
        int index = 0;
        for (int i = 0; i < 4; i++) {
            int opCnt = Integer.parseInt(st.nextToken());
            while (opCnt-- > 0) {
                operator[index++] = i;
            }
        }

        picked = new int[N - 1];
        visited = new boolean[N - 1];

        solve(0);

        System.out.println(ansMax);
        System.out.println(ansMin);
    }
}
