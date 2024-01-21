package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최적경로_1247 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int N;
    private static Point[] points;
    private static int[][] dist;
    private static int[] picked;
    private static boolean[] visited;
    private static int res;

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static int calcDist(int prev, int next) {
        return Math.abs(points[prev].y - points[next].y) + Math.abs(points[prev].x - points[next].x);
    }

    private static void shortestDistance(int index, int currentDist) {
        if(res <= currentDist) return;

        if(index == N + 1) {
            res = Math.min(res, currentDist + dist[picked[index - 1]][index]);
            return;
        }

        int prev = picked[index - 1];
        for(int next = 1; next <= N; next++) {
            if(!visited[next]) {
                visited[next] = true;
                picked[index] = next;
                shortestDistance(index + 1, currentDist + dist[prev][next]);
                visited[next] = false;
            }
        }
    }

    private static void solve() {
        picked = new int[N + 1];
        visited = new boolean[N + 1];

        dist = new int[N + 2][N + 2];
        for (int i = 0; i < N + 2; i++) {
            for (int j = 0; j < N + 2; j++) {
                dist[i][j] = dist[j][i] = calcDist(i, j);
            }
        }

        picked[0] = 0;
        visited[0] = true;
        shortestDistance(1, 0);
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            points = new Point[N + 2];
            points[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            points[N + 1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            for (int i = 1; i <= N; i++) {
                points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            res = Integer.MAX_VALUE;
            solve();
            System.out.println("#" + (t + 1) + " " + res);
        }
    }
}
