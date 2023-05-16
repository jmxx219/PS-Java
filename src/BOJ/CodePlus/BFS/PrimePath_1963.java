package BOJ.CodePlus.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PrimePath_1963 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static boolean[] isPrime;
    private static final int MAX = 9999;
    private static final int MIN = 1000;
    private static final int[] digit = {1000, 100, 10, 1};
    private static int A;
    private static int B;

    public static void prime() {
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i <= MAX; i++) {
            if(!isPrime[i]) continue;
            for (int j = i * i; j <= MAX ; j+=i) {
                isPrime[j] = false;
            }
        }
    }

    public static int nextNum(int here, int index, int n) {
        int next = 0;
        for (int i = 0; i < 4; i++) {
            if(i == index) next += (n * digit[i]);
            else next += here - (here % digit[i]);
            here %= digit[i];
        }
        return next;
    }

    public static int solve() {
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, -1);

        queue.add(A);
        dist[A] = 0;

        while (!queue.isEmpty()) {
            int here = queue.poll();

            if(here == B) return dist[B];

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    int next = nextNum(here, i, j);
                    if(next < MIN) continue;
                    if(isPrime[next] && dist[next] == -1) {
                        dist[next] = dist[here] + 1;
                        queue.add(next);
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        isPrime = new boolean[MAX + 1];
        prime();

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            int res = solve();
            if (res == -1) System.out.println("Impossible");
            else System.out.println(res);
        }
    }
}
