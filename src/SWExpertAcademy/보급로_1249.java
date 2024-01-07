package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 보급로_1249 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int N;
    private static int[][] map;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};
    private static final int INF = 987654321;

    static class Node implements Comparable<Node> {
        int y, x;
        int dist;

        public Node(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static int solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }

        pq.add(new Node(0, 0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Node here = pq.poll();

            if(here.y == N - 1 && here.x == N - 1) break;
            if(dist[here.y][here.x] < here.dist) continue;

            for (int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if(!isRange(ny, nx)) continue;

                if(dist[ny][nx] > here.dist + map[ny][nx]) {
                    dist[ny][nx] = here.dist + map[ny][nx];
                    pq.add(new Node(ny, nx, dist[ny][nx]));
                }
            }
        }

        return dist[N - 1][N - 1];
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int j = 0; j < N; j++) {
                map[j] = Arrays.stream(br.readLine().split(""))
                        .mapToInt(Integer::parseInt).toArray();
            }
            System.out.println("#" + (i + 1) + " " + solve());
        }
    }
}
