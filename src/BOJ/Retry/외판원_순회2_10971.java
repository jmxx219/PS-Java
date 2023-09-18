package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 외판원_순회2_10971 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] W;
    private static int[] picked;
    private static boolean[] visited;

    private static int shortestPath(int index, int currDist) {
        if(index == N - 1) {
            int here = picked[index];
            int next = picked[0];
            if(W[here][next] == 0) return Integer.MAX_VALUE;
            return W[here][next] + currDist;
        }

        int res = Integer.MAX_VALUE;

        int here = picked[index];
        for (int next = 0; next < N; next++) {
            if(W[here][next] == 0 || visited[next]) continue;
            picked[index + 1] = next;
            visited[next] = true;
            res = Math.min(res, shortestPath(index + 1, currDist + W[here][next]));
            visited[next] = false;
        }

        return res;
    }

    private static int solve() {
        int res = Integer.MAX_VALUE;

        picked = new int[N];
        visited = new boolean[N];
        for (int start = 0; start < N; start++) {
            visited[start] = true;
            picked[0] = start;
            res = Math.min(res, shortestPath(0, 0));
            visited[start] = false;
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        W = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }
}
