package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HideAndSeek4_13913 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static final int INF = 100000;

    private static void bfs() {
        int[] prev = new int[INF + 1];
        int[] dist = new int[INF + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);


        while (!queue.isEmpty()) {
            int x = queue.poll();

            if(x == K) break;

            if(x > 0 && dist[x - 1] == 0) {
                queue.add(x - 1);
                dist[x - 1] = dist[x] + 1;
                prev[x - 1] = x;
            }

            if(x < 100000 && dist[x + 1] == 0) {
                queue.add(x + 1);
                dist[x + 1] = dist[x] + 1;
                prev[x + 1] = x;
            }

            if(x * 2 <= 100000 && dist[x * 2] == 0) {
                queue.add(x * 2);
                dist[x * 2] = dist[x] + 1;
                prev[x * 2] = x;
            }
        }

        int[] res = new int[dist[K] + 1];
        int p = K;
        for(int i = dist[K]; i >= 0; i--) {
            res[i] = p;
            p = prev[p];
        }

        System.out.println(dist[K]);
        Arrays.stream(res).forEach(s -> System.out.print(s + " "));
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();
    }
}