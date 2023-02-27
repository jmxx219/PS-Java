package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HideAndSeek3_13549 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static final int MAX = 100001;

    private static int bfs2() {
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[MAX];
        Arrays.fill(dist, -1);

        queue.add(N);
        dist[N] = 0;
        while (!queue.isEmpty()) {
            int here = queue.poll();
            if(here == K) break;
            for (int next : new int[]{here * 2, here - 1, here + 1}) {
                if(next >= 0 && next < MAX && dist[next] == -1) {
                    queue.add(next);
                    if(here * 2 == next) { // 0초
                        dist[next] = dist[here];
                    }
                    else { // 1초
                        dist[next] = dist[here] + 1;
                    }
                }
            }
        }

        return dist[K];
    }

    private static int bfs() {
        boolean[] visited = new boolean[MAX];
        int[] dist = new int[MAX];
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> next_queue = new LinkedList<Integer>();

        queue.add(N);
        dist[N] = 0;
        visited[N] = true;
        while (!queue.isEmpty()) {
            int here = queue.poll();
            for (int next : new int[]{here * 2, here - 1, here + 1}) {
                if(next >= 0 && next < MAX && !visited[next]) {
                    visited[next] = true;
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
            if (queue.isEmpty()) {
                queue = next_queue;
                next_queue = new LinkedList<Integer>();
            }
        }

        return dist[K];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(bfs2());
    }
}
