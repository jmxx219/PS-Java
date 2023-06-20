package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.print.CancelablePrintJob;

public class AddParenthesis_16637 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static char[] op;
    private static int[] num;
    private static int res;

    private static int opCompute(int n1, char op, int n2) {
        switch (op) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
        }
        return 0;
    }

    private static int compute(boolean[] picked) {
        int prev = num[0];
        for (int i = 1; i < num.length; i++) {
            int next = 0;

            if(i < op.length && picked[i]) next = opCompute(num[i], op[i], num[i + 1]);
            else next = num[i];

            prev = opCompute(prev, op[i - 1], next);

            if(i < op.length && picked[i]) i += 1;
        }

        return prev;
    }

    private static void solve(int start, int index, boolean[] picked) {
        if(index > 0) {
            res = Math.max(res, compute(picked));
            if (index == op.length) return;
        }

        for (int i = start; i < op.length; i++) {
            if(picked[i]) continue;
            if(index == 0 || (index > 0 && !picked[i - 1])) {
                picked[i] = true;
                solve(i + 1, index + 1, picked);
                picked[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        num = new int[N / 2 + 1];
        op = new char[N / 2];

        char[] str = br.readLine().toCharArray();

        int nIdx = 0;
        int oIdx = 0;
        for (int i = 0; i < N; i++) {
            if(i % 2 == 0) num[nIdx++] = str[i] - '0';
            else op[oIdx++] = str[i];
        }

        if (N == 1) System.out.println(num[0]);
        else {
            res = Integer.MIN_VALUE;
            solve(0, 0, new boolean[op.length]);
            System.out.println(res);
        }
    }
}
