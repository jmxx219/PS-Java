package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질3_13549 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static final int MAX = 100_000;

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> next_queue = new LinkedList<>();
        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, -1);

        dist[N] = 0;
        queue.add(N);

        while (!queue.isEmpty()) {
            int here = queue.poll();

            if(here == K) break;

            for (int next : new int[]{here * 2, here - 1, here + 1}) {
                if(next >= 0 && next <= MAX && dist[next] == -1) {
                    if(here * 2 == next) { // 0초
                        queue.add(next);
                        dist[next] = dist[here];
                    }
                    else { // 1초
                        next_queue.add(next);
                        dist[next] = dist[here] + 1;
                    }
                }
            }

            if(queue.isEmpty()) {
                queue = next_queue;
                next_queue = new LinkedList<>();
            }
        }

        return dist[K];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(bfs());

    }
}
