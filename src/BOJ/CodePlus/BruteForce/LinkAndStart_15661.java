package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LinkAndStart_15661 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] startLink;
    private static boolean[] visited;
    private static int result = Integer.MAX_VALUE;

    private static int compute() {
        int start = 0;
        int link = 0;
        for(int i = 0; i < N - 1; i++) {
            for(int j = i + 1; j < N; j++) {
                if(visited[i] && visited[j]) start += (startLink[i][j] + startLink[j][i]);
                else if(!visited[i] && !visited[j]) link += (startLink[i][j] + startLink[j][i]);
            }
        }
        return Math.abs(start - link);
    }

    private static boolean combination(int index, int start, int M) {
        if(index == M) {
            result = Math.min(result, compute());
            if(result == 0) return true;
        }
        for(int i = start; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                if(combination(index + 1, i + 1, M)) return true;
                visited[i] = false;
            }
        }
        return false;
    }

    private static void solve() {
        for(int i = 1; i < N; i++) {
            if(combination(0, 0, i)) return;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        startLink = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                startLink[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];
        solve();
        System.out.println(result);
    }
}
