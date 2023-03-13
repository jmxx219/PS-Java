package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DSLR_9019 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int A;
    private static int B;
    private static final int MAX = 10000;
    private static final char[] REGISTER = {'D', 'S', 'L', 'R'};

    private static int compute(int here, int register) {
        if(register == 0) {
            here = (here * 2) % 10000;
        }
        else if(register == 1) {
            here -= 1;
            if(here == -1) here = MAX - 1;
        }
        else if(register == 2) {
            here = (here % 1000) * 10 + here / 1000;
        }
        else {
            here = (here / 10) + (here % 10) * 1000;
        }
        return here;
    }

    private static void bfs(int[] how, int[] prev) {
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(how, - 1);

        queue.add(A);
        how[A] = 0;

        while (!queue.isEmpty()) {
            int here = queue.poll();
            if(here == B) return;
            for(int i = 0; i < 4; i++) {
                int next = compute(here, i);
                if(next < 0 || next >= MAX) continue;
                if(how[next] == -1) {
                    queue.add(next);
                    how[next] = i;
                    prev[next] = here;
                }
            }
        }
    }

    private static StringBuilder solve() {
        int[] how = new int[MAX + 1];
        int[] prev = new int[MAX + 1];

        bfs(how, prev);

        StringBuilder sb = new StringBuilder();
        int here = B;
        while (here != A) {
            sb.append(REGISTER[how[here]]);
            here = prev[here];
        }

        return sb.reverse();
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            System.out.println(solve());
        }
    }
}
