package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 수영대회_결승전_4193 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int N;
    private static int[][] map;
    private static Node start;
    private static Node end;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};

    static class Node {
        int y;
        int x;
        int dist;
        public Node(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static int solve() {
        Queue<Node> q = new LinkedList<>();
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        q.add(start);
        dist[start.y][start.x] = 0;

        while(!q.isEmpty()) {
            Node here = q.poll();

            if(here.y == end.y && here.x == end.x) return here.dist;

            for(int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if(!isRange(ny, nx)) continue;
                if(map[ny][nx] == 1) continue;

                boolean hidden = (here.dist + 1)  % 3 == 0;
                if(map[ny][nx] == 2 && !hidden) {
                    q.add(new Node(here.y, here.x, here.dist + 1));
                    continue;
                }

                if(dist[ny][nx] == -1 || dist[ny][nx] > here.dist + 1) {
                    dist[ny][nx] = here.dist + 1;
                    q.add(new Node(ny, nx, here.dist + 1));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            start = new Node(y, x, 0);

            st = new StringTokenizer(br.readLine());
            y = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            end = new Node(y, x, 0);

            System.out.println("#" + (t + 1) + " " + solve());
        }
    }
}
