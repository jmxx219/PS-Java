package BOJ.CodePlus.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class SnakeLadderGame_16928 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[] board;
    private static final int SIZE = 100;
    private static Map<Integer, Integer> snakeLadder;


    private static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[SIZE + 1];
        Arrays.fill(dist, - 1);

        queue.add(start);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int here = queue.poll();

            for (int i = 1; i <= 6; i++) {
                int next = here + i;
                if(next > SIZE) continue;

                if(snakeLadder.containsKey(next)) next = snakeLadder.get(next);

                if(dist[next] == -1) {
                    dist[next] = dist[here] + 1;
                    queue.add(next);
                }
            }
        }

        return dist[SIZE];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[SIZE + 1];
        snakeLadder = new HashMap<>();
        for(int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            snakeLadder.put(u, v);
        }

        System.out.println(bfs(1));
    }
}
