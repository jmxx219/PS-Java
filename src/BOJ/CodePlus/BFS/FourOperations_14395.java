package BOJ.CodePlus.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class FourOperations_14395 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static long S;
    private static long T;
    private static final long limit = 1000000000L;

    private static class Operation {
        long n;
        String op;

        public Operation(long n, String op) {
            this.n = n;
            this.op = op;
        }
    }

    private static String solve() {
        if(S == T) return "0";

        Set<Long> visited = new HashSet<>();
        Queue<Operation> q = new LinkedList<>();

        q.add(new Operation(S, ""));
        visited.add(S);

        while (!q.isEmpty()) {
            Operation here = q.poll();
            long x = here.n;
            String op = here.op;

            if(x == T) return op;

            if( 0 <= x * x && x * x <= limit && !visited.contains(x * x)) {
                q.add(new Operation(x * x, op + "*"));
                visited.add(x * x);
            }
            if( 0 <= x + x && x + x <= limit && !visited.contains(x + x)) {
                q.add(new Operation(x + x, op + "+"));
                visited.add(x + x);
            }
            if( 0 <= x - x && x - x <= limit && !visited.contains(x - x)) {
                q.add(new Operation(x - x, op + "-"));
                visited.add(x - x);
            }
            if(x != 0 && 0 <= x / x && x / x <= limit && !visited.contains(x / x)) {
                q.add(new Operation(x / x, op + "/"));
                visited.add(x / x);
            }
        }

        return "-1";
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        S = Long.parseLong(st.nextToken());
        T = Long.parseLong(st.nextToken());

        System.out.println(solve());
    }
}
