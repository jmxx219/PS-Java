package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Pass(20m)
public class 연산자_끼워넣기_14888 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] A;
    private static int[] op; // +, -, *, / => 0, 1, 2, 3
    private static int RES_MAX;
    private static int RES_MIN;

    private static int calc(int prev, int next, int op) {
        if(op == 0) return prev + next;
        else if(op == 1) return prev - next;
        else if(op == 2) return prev * next;
        return prev / next;
    }

    private static void solve(int index, boolean[] visited, int currCalc) {
        if(index == N - 1) {
            if(currCalc > RES_MAX) RES_MAX = currCalc;
            if(currCalc < RES_MIN) RES_MIN = currCalc;
            return;
        }

        for (int i = 0; i < N - 1; i++) {
            if(!visited[i]) {
                visited[i] = true;
                solve(index + 1, visited, calc(currCalc, A[index + 1], op[i]));
                visited[i] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        op = new int[N - 1];
        int index = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            int x = Integer.parseInt(st.nextToken());
            for (int j = x; j > 0; j--) {
                op[index++] = i;
            }
        }

        RES_MAX = Integer.MIN_VALUE;
        RES_MIN = Integer.MAX_VALUE;

        solve(0, new boolean[N - 1], A[0]);

        System.out.println(RES_MAX);
        System.out.println(RES_MIN);

    }
}
