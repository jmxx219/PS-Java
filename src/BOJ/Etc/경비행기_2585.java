package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// F: 다익스트라 시간초과! 이분 탐색 + BFS ..
public class 경비행기_2585 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static final int T = 10000;
    private static int maxFuel;
    private static int[][] airfield;

    private static int calcFuel(int x1, int y1, int x2, int y2) {
        double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        int fuel = (int) (distance / 10 + (distance % 10 == 0 ? 0 : 1));

        return fuel;
    }

    private static boolean bfs(int fuel, int k) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];
        int[] dist = new int[N];

        queue.add(-1);

        while (!queue.isEmpty()) {
            int here = queue.poll();

            if(here != -1){
                if(dist[here] > k) return false;
                if(calcFuel(airfield[here][0], airfield[here][1], T, T) <= fuel) return true;
            }

            for (int next = 0; next < N; next++) {
                int nextY = airfield[next][0];
                int nextX = airfield[next][1];

                int nextFuel = (here == -1) ? calcFuel(0, 0, nextY, nextX)
                        : calcFuel(airfield[here][0], airfield[here][1], nextY, nextX);

                if(nextFuel <= fuel && !visited[next]){
                    visited[next] = true;
                    dist[next] = (here == -1 ? 0 : dist[here]) + 1;
                    queue.add(next);
                }
            }
        }

        return false;
    }

    private static int solve() {
        int ans = maxFuel;
        int left = 0, right = maxFuel;

        while(left <= right){
            int mid = (left + right) / 2;
            if(bfs(mid, K)){
                ans = mid;
                right = mid - 1;
            }
            else left = mid + 1;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        airfield = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            airfield[i][0] = Integer.parseInt(st.nextToken());
            airfield[i][1] = Integer.parseInt(st.nextToken());
        }

        maxFuel = calcFuel(0, 0, T, T);
        System.out.println(solve());
    }
}
